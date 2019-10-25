/**
 * 
 */
package com.carmargut.microservice.assets;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.carmargut.microservice.utils.RandomStringGenerator;


/**
 * Bank account
 * @author carmargut
 *
 */
@Entity
public class Account {
	
	public Account() {
		this.account_iban = RandomStringGenerator.createNewIBAN();
		this.balance = 0.0;
	}
	
	@Id
	private String account_iban;
	
	private double balance;

	public String getAccount_iban() {
		return account_iban;
	}

	public void setAccount_iban(String account_iban) {
		this.account_iban = account_iban;
	}

	public double getBalance() {
		return balance;
	}
 
	public void updateBalance(double quantity) {
		if(this.balance + quantity >= 0) {
			this.balance = balance + quantity;
		} else {
			throw new IllegalArgumentException("Not enough founds");
		}
	}

	@Override
	public String toString() {
		return "Account [account_iban=" + account_iban + ", balance=" + balance + "]";
	}
	
	
}
