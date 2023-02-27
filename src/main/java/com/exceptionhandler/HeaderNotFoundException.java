package com.exceptionhandler;

public class HeaderNotFoundException extends RuntimeException {
    public HeaderNotFoundException(String message) {
        super(message);
    }
}
