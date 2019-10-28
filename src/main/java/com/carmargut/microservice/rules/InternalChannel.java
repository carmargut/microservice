package com.carmargut.microservice.rules;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.carmargut.microservice.assets.Transaction;
import com.carmargut.microservice.rules.status.*;

/**
 * @author carmargut
 *
 */
public class InternalChannel implements Channel {

	@Override
	public Status getStatus(Transaction transaction) {
		
		LocalDate today = LocalDate.now();
		LocalDate transactionDate = transaction.getDate().toLocalDate();
		
		Map<String,String> amountAndFee = new HashMap<String,String>();

		amountAndFee.put("amount", String.valueOf(transaction.getAmount()));
		amountAndFee.put("fee", String.valueOf(transaction.getFee()));
		
		if(transactionDate.isBefore(today)) {
			return new SettledStatus(transaction.getReference(),amountAndFee);
		}
		
		if(transactionDate.isEqual(today)) {
			return new PendingStatus(transaction.getReference(),amountAndFee);
		}
		
		if(transactionDate.isAfter(today)) {
			return new FutureStatus(transaction.getReference(),amountAndFee);
		}
		
		return null; // Unreachable code
	}
	
}
