package com.carmargut.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.carmargut.microservice.assets.*;

import java.time.LocalDateTime;
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
	public @ResponseBody List<Transaction> getAllIngredients() {

		LOGGER.info("Accessing to / access point");
		return tr.findAll();
	}

	@PostMapping(path = "/createtransaction")
	@ResponseBody
	public Transaction createTransaction(@RequestParam(value = "reference", required = false) String reference,
			@RequestParam(value = "account_iban", required = true) String account_iban,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "amount", required = true) String amount,
			@RequestParam(value = "fee", defaultValue = "0") String fee,
			@RequestParam(value = "description", required = false) String description) {

		LOGGER.info("Accessing to /createtransaction access point");

		Transaction t = new Transaction();
		t.setReference(reference);

		Optional<Account> optionalAccount = ar.findById(account_iban);
		Account account;
		if (optionalAccount.isPresent()) {
			account = optionalAccount.get();
			t.setAccount_iban(account);
			ar.save(account);
		} else {
			account = new Account();
			t.setAccount_iban(account);
			ar.save(account);
		}

			t.setDate(LocalDateTime.now());
		t.setAmount(Double.valueOf(amount));
		t.setFee(Double.valueOf(fee));
		t.setDescription(description);

		LOGGER.debug("Transaction created: " + t.toString());
		tr.save(t);
		return t;
	}

}
