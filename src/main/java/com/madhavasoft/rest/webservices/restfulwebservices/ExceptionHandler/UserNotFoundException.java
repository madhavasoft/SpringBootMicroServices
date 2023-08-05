package com.madhavasoft.rest.webservices.restfulwebservices.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
		System.out.println("UserNotFoundException Constructor");
	}

}
