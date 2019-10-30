package com.carmargut.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.carmargut.microservice.assets.*;
import com.carmargut.microservice.exceptions.MicroserviceException;
import com.carmargut.microservice.exceptions.account.AccountNotFoundException;
import com.carmargut.microservice.exceptions.parameters.BadOrderParameterException;
import com.carmargut.microservice.exceptions.parameters.ExistingTransactionException;
import com.carmargut.microservice.rules.ATMChannel;
import com.carmargut.microservice.rules.Channel;
import com.carmargut.microservice.rules.ClientChannel;
import com.carmargut.microservice.rules.InternalChannel;
import com.carmargut.microservice.rules.status.InvalidStatus;
import com.carmargut.microservice.rules.status.Status;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);

	@Autowired
	private TransactionRepository tr;
	@Autowired
	private AccountRepository ar;

	/**
	 * Gets all accounts
	 * 
	 * @return
	 */
	@GetMapping(path = "/")
	public @ResponseBody List<Account> getAll() {

		LOGGER.info("Accessing to / access point");
		return ar.findAll();
	}

	/**
	 * Create a new transaction and a new account if it does not exist
	 * 
	 * @param reference
	 * @param account_iban mandatory
	 * @param date
	 * @param amount       mandatory
	 * @param fee
	 * @param description
	 * @return
	 * @throws MicroserviceException
	 */
	@PostMapping(path = "/createtransaction")
	@ResponseBody
	public Transaction createTransaction(@RequestParam(value = "reference", required = false) String reference,
			@RequestParam(value = "account_iban", required = true) String account_iban,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "amount", required = true) String amount,
			@RequestParam(value = "fee", defaultValue = "0") String fee,
			@RequestParam(value = "description", required = false) String description) throws MicroserviceException {

		LOGGER.info("Accessing to /createtransaction access point");

		// Avoids create a new one with an existing reference
		if (reference != null && tr.findById(reference).isPresent()) {
			throw new ExistingTransactionException();
		}

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

	// I assume the parameters are passed by query since it's a get request.
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

	// I assume the parameters are passed by query since it's a get request.
	@GetMapping(path = "/getstatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> getStatus(@RequestParam(value = "reference", required = true) String reference,
			@RequestParam(value = "channel", required = false) String channelName) throws MicroserviceException {

		Optional<Transaction> optTransaction = tr.findById(reference);
		Status status;

		// I don't know why channelName is optional so I return an invalid status
		if (channelName == null) {
			status = new InvalidStatus(reference);
		} else {

			if (optTransaction.isPresent()) {

				Transaction transaction = optTransaction.get();
				Channel channel = null;

				switch (channelName.toUpperCase()) {
				case "ATM":
					channel = new ATMChannel();
					break;
				case "CLIENT":
					channel = new ClientChannel();
					break;
				case "INTERNAL":
					channel = new InternalChannel();
					break;
				default: // if the channel name is wrong, I return an invalid status
					return new InvalidStatus(reference).getResponse();
				}

				status = channel.getStatus(transaction);

			} else {
				status = new InvalidStatus(reference);
			}
		}

		return status.getResponse(); // Gets the JSON format response

	}

}
