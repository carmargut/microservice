/**
 * 
 */
package com.carmargut.microservice.assets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.carmargut.microservice.exceptions.*;
import com.carmargut.microservice.exceptions.fees.FeeException;
import com.carmargut.microservice.exceptions.fees.InvalidFeeException;
import com.carmargut.microservice.exceptions.fees.NegativeFeeException;
import com.carmargut.microservice.exceptions.parameters.InvalidAmountException;
import com.carmargut.microservice.exceptions.parameters.WrongDateFormatException;
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

	public Transaction() {
	}

	public Transaction(String reference, String date, String amount, String fee, String description)
			throws MicroserviceException {
		if (reference != null) {
			this.reference = reference;
		} else {
			this.reference = RandomStringGenerator.createNewReference();
		}

		try {
			this.date = LocalDateTime.parse(date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		} catch (DateTimeParseException dtpe) {
			throw new WrongDateFormatException();
		} catch (NullPointerException e) {
			// I assume that if no date has been entered, today's date is added.
			this.date = LocalDateTime.now();
		}
		setAmount(amount);
		setFee(fee);
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public double getFee() {
		return fee;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	public String getReference() {
		return reference;
	}

	private void setAmount(String amount) throws InvalidAmountException {
		try {
			this.amount = Double.valueOf(amount);
		} catch (NumberFormatException e) {
			throw new InvalidAmountException();
		}
	}

	private void setFee(String stringFee) throws FeeException {
		double fee;
		try {
			fee = Double.valueOf(stringFee);
			if (fee >= 0) {
				this.fee = fee;
			} else {
				throw new NegativeFeeException();
			}
		} catch (NumberFormatException e) {
			throw new InvalidFeeException();
		}

	}

	@Override
	public String toString() {
		return "Transaction [reference=" + reference + ", date=" + date + ", amount=" + amount + ", fee=" + fee
				+ ", description=" + description + "]";
	}

}
