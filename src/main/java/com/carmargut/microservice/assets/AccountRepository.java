package com.carmargut.microservice.assets;

import java.util.Optional;
import org.springframework.data.repository.Repository;


public interface AccountRepository extends Repository<Account,String>{
	
	/**
	 * Returns an account	
	 * @param account_iban iban of the account
	 * @return 
	 */
	Optional<Account> findById(String account_iban);

	void save(Account account);

}
