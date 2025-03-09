package com.sergiocuacor.user_service.commons.exceptions;

import org.springframework.http.HttpStatus;

public class UserException  extends RuntimeException{

	private final String errorCode;
	private final HttpStatus httpStatus;
	public UserException(String errorCode, String message ,HttpStatus httpStatus) {
		super(message);
		this.errorCode = errorCode;
		this.httpStatus = httpStatus;
	}
	
	
}
