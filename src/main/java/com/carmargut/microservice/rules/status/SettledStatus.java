package com.carmargut.microservice.rules.status;

import java.util.HashMap; 
import java.util.Map;

import com.carmargut.microservice.utils.StatusResponseEnum;

/**
 * @author carmargut
 *
 */
public class SettledStatus extends Status {

	public SettledStatus(String reference, Map<String, String> amountAndFee) {
		super(reference, StatusResponseEnum.SETTLED, amountAndFee);
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
