package com.sergiocuacor.user_service.commons.exceptions.user_exceptions;

import org.springframework.http.HttpStatus;

import com.sergiocuacor.user_service.commons.exceptions.UserException;

public class UserNotFoundException extends UserException{

	public UserNotFoundException(Long userId) {
        super(
            "USER_001",
            "No se encontró el usuario con ID: "+userId,
            HttpStatus.NOT_FOUND
        );
    }
    
    public UserNotFoundException(String email) {
        super(
            "USER_001",
            "No se encontró el usuario con email: "+ email,
            HttpStatus.NOT_FOUND
        );
    }

}
