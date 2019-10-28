package com.carmargut.microservice.rules.status;

/**
 * @author carmargut
 *
 */
public class PendingStatus extends Status {

	/**
	 * @param reference
	 */
	public PendingStatus(String reference) {
		super(reference, Response.PENDING);
	}

}
