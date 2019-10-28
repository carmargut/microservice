package com.carmargut.microservice.rules.status;

/**
 * @author carmargut
 *
 */
public class FutureStatus extends Status {

	/**
	 * @param reference
	 */
	public FutureStatus(String reference) {
		super(reference, Response.FUTURE);
	}

}
