package com.superhero.lab.exceptionhandler;

public class SuperHeroNotFoundIdException extends RuntimeException {
    public SuperHeroNotFoundIdException(String message) {
        super(message);
    }
}
