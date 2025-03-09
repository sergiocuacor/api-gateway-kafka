package com.sergiocuacor.auth_service.commons.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Excepción base para todos los errores relacionados con autenticación.
 * Proporciona información sobre el código de error, mensaje y estado HTTP.
 */
@Getter
public class AuthException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;

   
    public AuthException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

   
    
}