/**
 * 
 */
package com.carmargut.microservice.assets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.carmargut.microservice.exceptions.WrongDateFormat;
import com.carmargut.microservice.utils.RandomStringGenerator;

/**
 * @author carmargut
 *
 */
@Entity
public class Transaction {

	@Id
	private String reference;
	private LocalDateTime date;
	private double amount;
	private double fee;
	private String description;
	
	public Transaction() {}

	public Transaction(String reference, String date, String amount, String fee, String description) {
		if (reference != null) {
			this.reference = reference;
		} else {
			this.reference = RandomStringGenerator.createNewReference();
		}
		
		try {
			this.date = LocalDateTime.parse(date,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		} catch(DateTimeParseException dtpe) {
			throw new WrongDateFormat();
		} catch (NullPointerException e) {
			this.date = null;
		}
		this.amount = Double.valueOf(amount);
		this.fee = Double.valueOf(fee);
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public double getFee() {
		return fee;
	}

	@Override
	public String toString() {
		return "Transaction [reference=" + reference + ", date=" + date + ", amount=" + amount + ", fee=" + fee
				+ ", description=" + description + "]";
	}
	
	

}
