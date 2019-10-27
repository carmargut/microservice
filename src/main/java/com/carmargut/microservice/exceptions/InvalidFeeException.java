package com.carmargut.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid fee format")
public class InvalidFeeException extends FeeException {

	private static final long serialVersionUID = 1L;

}
