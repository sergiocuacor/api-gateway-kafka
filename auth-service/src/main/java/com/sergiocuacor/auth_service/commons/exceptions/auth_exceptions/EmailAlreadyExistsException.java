package com.sergiocuacor.auth_service.commons.exceptions.auth_exceptions;

import org.springframework.http.HttpStatus;

import com.sergiocuacor.auth_service.commons.exceptions.AuthException;

public class EmailAlreadyExistsException extends AuthException {
    public EmailAlreadyExistsException(String email) {
        super(
            "AUTH_002",
            String.format("El email '%s' ya está registrado", email),
            HttpStatus.CONFLICT
        );
    }
}