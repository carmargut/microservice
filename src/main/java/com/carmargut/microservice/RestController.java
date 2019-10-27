package com.carmargut.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;
import com.carmargut.microservice.assets.*;
import com.carmargut.microservice.exceptions.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);

	@Autowired
	private TransactionRepository tr;
	@Autowired
	private AccountRepository ar;

	@GetMapping(path = "/")
	public @ResponseBody List<Account> getAll() {

		LOGGER.info("Accessing to / access point");
		return ar.findAll();
	}

	@PostMapping(path = "/createtransaction")
	@ResponseBody
	public Transaction createTransaction(@RequestParam(value = "reference", required = false) String reference,
			@RequestParam(value = "account_iban", required = true) String account_iban,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "amount", required = true) String amount,
			@RequestParam(value = "fee", defaultValue = "0") String fee,
			@RequestParam(value = "description", required = false) String description) throws MicroserviceException {

		LOGGER.info("Accessing to /createtransaction access point");

		Transaction transaction = new Transaction(reference, date, amount, fee, description);

		// I assume the only way to create accounts is to insert them with a new
		// transaction.
		Account account;
		Optional<Account> optionalAccount = ar.findById(account_iban);
		if (optionalAccount.isPresent()) {
			account = optionalAccount.get();
		} else {
			account = new Account(account_iban);
			ar.save(account);
		}

		account.setTransaction(transaction);
		tr.save(transaction);
		ar.save(account);

		return transaction;
	}

	@GetMapping(path = "/searchtransactions")
	@ResponseBody
	public List<Transaction> searchTransactions(
			@RequestParam(value = "account_iban", required = false) String account_iban,
			@RequestParam(value = "amountOrder", defaultValue = "asc", required = false) String order)
			throws MicroserviceException {

		LOGGER.info("Accessing to /gettransactions access point");

		Direction direction;
		switch (order) {
		case "asc":
			direction = Sort.Direction.ASC;
			break;
		case "desc":
			direction = Sort.Direction.DESC;
			break;
		default:
			throw new BadOrderParameterException();
		}

		List<Transaction> list;

		if (account_iban != null) {
			Optional<Account> account = ar.findById(account_iban);
			if (account.isPresent()) {
				list = account.get().getTransactionList();
			} else {
				throw new AccountNotFoundException();
			}
			switch (order) {
			case "asc":
				list.sort(Comparator.comparing(Transaction::getAmount));
				break;
			case "desc":
				list.sort(Comparator.comparing(Transaction::getAmount).reversed());
				break;
			}
		} else {
			list = tr.findAll(Sort.by(direction, "amount")); 
		}

		return list;
	}

}
