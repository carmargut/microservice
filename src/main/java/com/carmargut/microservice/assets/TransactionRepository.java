package com.carmargut.microservice.assets;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface TransactionRepository extends Repository<Transaction,String>{
	
	/**
	 * Returns a transaction	
	 * @param reference Reference of the account
	 * @return 
	 */
	Optional<Transaction> findById(String reference);

	/**
	 * Return all transactions
	 * @return
	 */
	List<Transaction> findAll();

	void save(Transaction t);

}