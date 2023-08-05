package com.madhavasoft.rest.webservices.restfulwebservices.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
//https://ibm-learning.udemy.com/course/microservices-with-spring-boot-and-spring-cloud/learn/lecture/33578796#overview
public class GenericExceptionDetails  {

	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	
	public GenericExceptionDetails(LocalDateTime timestamp, String message, String details) {
		// TODO Auto-generated constructor stub
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "GenericExceptionDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
	
}
