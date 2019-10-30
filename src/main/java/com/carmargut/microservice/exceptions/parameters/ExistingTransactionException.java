package com.carmargut.microservice.exceptions.parameters;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.carmargut.microservice.exceptions.MicroserviceException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The transaction already exists")
public class ExistingTransactionException extends MicroserviceException {
	
	private static final long serialVersionUID = 1L;
	
}
