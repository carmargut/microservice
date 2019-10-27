package com.carmargut.microservice.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Balance cannot be negative")
public class InsufficientBalanceException extends MicroserviceException {
	
	private static final long serialVersionUID = 1L;

}
