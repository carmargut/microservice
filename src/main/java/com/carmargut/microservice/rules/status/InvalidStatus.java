package com.carmargut.microservice.rules.status;

import java.util.HashMap;
import java.util.Map;

/**
 * @author carmargut
 *
 */
public class InvalidStatus extends Status {

	public InvalidStatus(String reference, Map<String, String> amountAndFee) {
		super(reference, Response.INVALID, amountAndFee);
	}

	@Override
	public Map<String, String> getResponse() {
		Map<String, String> response = new HashMap<String, String>();

		response.put("reference", getReference());
		response.put("status", getStatus());

		for (Map.Entry<String, String> entry : amountAndFee.entrySet()) {
			response.put(entry.getKey(), entry.getValue());
		}

		return response;
	}

}
