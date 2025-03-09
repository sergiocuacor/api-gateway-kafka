package com.sergiocuacor.auth_service.commons.exceptions.auth_exceptions;

import org.springframework.http.HttpStatus;

import com.sergiocuacor.auth_service.commons.exceptions.AuthException;

public class InvalidCredentialsException extends AuthException {
    public InvalidCredentialsException() {
        super(
            "AUTH_001",
            "Las credenciales proporcionadas son inválidas",
            HttpStatus.UNAUTHORIZED
        );
    }
}