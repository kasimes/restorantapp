package com.ornek.restorant.restorantapp.exception;

public class OrderItemNotFoundException extends NotFoundException {
    public OrderItemNotFoundException(String message) {
        super(message);
    }
}
