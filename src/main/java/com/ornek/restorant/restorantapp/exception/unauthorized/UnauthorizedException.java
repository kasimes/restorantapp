package com.ornek.restorant.restorantapp.exception.unauthorized;

import jakarta.servlet.UnavailableException;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
