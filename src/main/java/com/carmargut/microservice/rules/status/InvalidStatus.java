package com.carmargut.microservice.rules.status;

/**
 * @author carmargut
 *
 */
public class InvalidStatus extends Status {

	
	public InvalidStatus(String reference) {
		super(reference,Response.INVALID);
	}

}
