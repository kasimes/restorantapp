package com.ornek.restorant.restorantapp.exception.unauthorized;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
