package com.carmargut.microservice.rules.status;

/**
 * @author carmargut
 *
 */
public class SettledStatus extends Status {

	/**
	 * @param reference
	 */
	public SettledStatus(String reference) {
		super(reference, Response.SETTLED);
	}

}
