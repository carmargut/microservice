package com.carmargut.microservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="We can't recognize the date")
public class WrongDateFormat extends RuntimeException{ 
	private static final long serialVersionUID = 1L;
}
