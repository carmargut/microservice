package com.carmargut.microservice.rules.status;

/**
 * @author carmargut
 *
 */
public abstract class Status {

	private String reference;
	private Response status;

	public Status(String reference, Response status) {
		this.reference = reference;
		this.status = status;
	}

	public String getReference() {
		return reference;
	}

	public Response getStatus() {
		return status;
	}

}
