package com.madhavasoft.rest.webservices.restfulwebservices.ExceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<GenericExceptionDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		GenericExceptionDetails genericException = new GenericExceptionDetails(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<GenericExceptionDetails>(genericException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<GenericExceptionDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		GenericExceptionDetails genericException = new GenericExceptionDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<GenericExceptionDetails>(genericException, HttpStatus.NOT_FOUND);
		}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		GenericExceptionDetails genericException = new GenericExceptionDetails(LocalDateTime.now(), 
				"Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));
				//ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(genericException, HttpStatus.BAD_REQUEST);
	}
	
}