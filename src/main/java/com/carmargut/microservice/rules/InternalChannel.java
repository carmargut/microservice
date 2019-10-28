package com.carmargut.microservice.rules;

import java.time.LocalDate;

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
		
		if(transactionDate.isBefore(today)) {
			return new SettledStatus(transaction.getReference());
		}
		
		if(transactionDate.isEqual(today)) {
			return new PendingStatus(transaction.getReference());
		}
		
		if(transactionDate.isAfter(today)) {
			return new FutureStatus(transaction.getReference());
		}
		
		return null; // Unreachable code
	}
	
}
