package com.carmargut.microservice.assets;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;


public interface AccountRepository extends Repository<Account,String>{
	
	/**
	 * Returns an account	
	 * @param account_iban iban of the account
	 * @return 
	 */
	Optional<Account> findById(String account_iban);

	/**
	 * Saves the entity into the database
	 * @param account
	 */
	void save(Account account);

	/**
	 * Gets all accounts
	 * @return
	 */
	List<Account> findAll();

}
