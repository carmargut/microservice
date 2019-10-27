package com.carmargut.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The account does not exist")
public class AccountNotFoundException extends MicroserviceException {

	private static final long serialVersionUID = 1L;

}
