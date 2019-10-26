/**
 * 
 */
package com.carmargut.microservice.assets;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.carmargut.microservice.exceptions.InsufficientBalanceException;
import com.carmargut.microservice.utils.RandomStringGenerator;

/**
 * Bank account
 * 
 * @author carmargut
 *
 */
@Entity
public class Account {

	@Id
	private String account_iban;
	@OneToMany
	private List<Transaction> transactionList;
	private double balance;

	public Account() {
		this.account_iban = RandomStringGenerator.createNewIBAN();
		this.transactionList = new ArrayList<Transaction>();
		this.balance = 0.0;
	}

	public Account(String account_iban) {
		this.account_iban = account_iban;
		this.transactionList = new ArrayList<Transaction>();
		this.balance = 0.0;
	}

	public String getAccount_iban() {
		return account_iban;
	}

	public double getBalance() {
		return balance;
	}

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	@Override
	public String toString() {
		return "Account [account_iban=" + account_iban + ", transactions=" + transactionList + ", balance=" + balance
				+ "]";
	}

	/**
	 * Add a transaction into the account
	 * 
	 * @param transaction
	 * @throws InsufficientBalanceException If the transaction leaves the total
	 *                                      account balance below 0
	 */
	public void setTransaction(Transaction transaction) throws InsufficientBalanceException {

		double totalAmount = transaction.getAmount() - transaction.getFee();

		if (this.balance + totalAmount >= 0) {
			this.balance = this.balance + totalAmount;
			this.transactionList.add(transaction);
		} else {
			throw new InsufficientBalanceException();
		}

	}

}
