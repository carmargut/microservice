package com.carmargut.microservice.rules.status;

import java.util.Map;

/**
 * @author carmargut
 *
 */
public abstract class Status {

	private String reference;
	private Response status;
	
	Map<String,String> amountAndFee;

	public Status(String reference, Response status, Map<String, String> amountAndFee) {
		this.setReference(reference);
		this.setStatus(status);
		this.amountAndFee = amountAndFee;
	}

	public abstract Map<String,String> getResponse();

	protected String getReference() {
		return reference;
	}

	protected void setReference(String reference) {
		this.reference = reference;
	}

	protected String getStatus() {
		return status.toString();
	}

	protected void setStatus(Response status) {
		this.status = status;
	}
}
