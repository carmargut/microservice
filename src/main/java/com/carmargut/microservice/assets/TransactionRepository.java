package com.carmargut.microservice.assets;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
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
	 * @param sort 
	 * @return
	 */
	List<Transaction> findAll();

	/**
	 * Saves the entity into the database
	 * @param transaction
	 */
	void save(Transaction transaction);

	/**
	 * Returns a list of transactions ordered	
	 * @param sort Enumeration for sort directions
	 * @return
	 */
	List<Transaction> findAll(Sort sort);
	 
}