/**
 * 
 */
package com.carmargut.microservice.assets;

import java.time.LocalDateTime; 

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.carmargut.microservice.utils.RandomStringGenerator;

/**
 * @author carmargut
 *
 */
@Entity
public class Transaction {

	@Id
	private String reference;

	@ManyToOne(targetEntity = Account.class)
	private Account account_iban;

	private LocalDateTime date;

	private double amount;

	private double fee;

	private String description;
 

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) { 
		if (reference != null) {
			this.reference = reference;
		} else {
			this.reference = RandomStringGenerator.createNewReference();
		}
	}

	public Account getAccount_iban() {
		return account_iban;
	}

	public void setAccount_iban(Account account_iban) {
		this.account_iban = account_iban;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [reference=" + reference + ", account_iban=" + account_iban + ", date=" + date + ", amount="
				+ amount + ", fee=" + fee + ", description=" + description + "]";
	}
 

}
