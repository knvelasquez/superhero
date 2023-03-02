package com.exceptionhandler;

public class SuperHeroNotFoundNameException extends RuntimeException {
    public SuperHeroNotFoundNameException(String message) {
        super(message);
    }
}
