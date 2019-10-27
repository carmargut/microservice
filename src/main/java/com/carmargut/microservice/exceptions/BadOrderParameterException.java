package com.carmargut.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Order must be 'desc' or 'asc'")
public class BadOrderParameterException extends MicroserviceException {

	private static final long serialVersionUID = 1L;

}
