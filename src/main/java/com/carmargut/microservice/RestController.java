package com.carmargut.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.carmargut.microservice.assets.*;
import com.carmargut.microservice.exceptions.*;

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

}
