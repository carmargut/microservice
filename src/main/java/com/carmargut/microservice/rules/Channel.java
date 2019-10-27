package com.carmargut.microservice.rules;

import com.carmargut.microservice.assets.Transaction;
import com.carmargut.microservice.rules.status.Status;

/**
 * @author carmargut
 *
 */
public interface Channel {
	
	/**
	 * Will return the status and additional information for a specific transaction
	 * @param transaction
	 * @return
	 */
	public Status getStatus(Transaction transaction);

}
