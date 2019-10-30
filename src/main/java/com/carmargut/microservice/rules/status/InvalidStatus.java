package com.carmargut.microservice.rules.status;

import java.util.HashMap;
import java.util.Map;

import com.carmargut.microservice.utils.StatusResponseEnum;

/**
 * @author carmargut
 *
 */
public class InvalidStatus extends Status {

	public InvalidStatus(String reference) {
		super(reference, StatusResponseEnum.INVALID, null);
	}

	@Override
	public Map<String, String> getResponse() {
		Map<String, String> response = new HashMap<String, String>();

		response.put("reference", getReference());
		response.put("status", getStatus());
 

		return response;
	}

}
