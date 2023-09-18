package com.superhero.lab.exceptionhandler;

public class SuperHeroNotFoundNameException extends RuntimeException {
    public SuperHeroNotFoundNameException(String message) {
        super(message);
    }
}
