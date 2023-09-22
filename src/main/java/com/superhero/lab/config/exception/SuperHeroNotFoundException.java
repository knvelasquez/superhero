package com.superhero.lab.config.exception;

public class SuperHeroNotFoundException extends Exception {
    public SuperHeroNotFoundException(String message) {
        super(new StringBuilder("SuperHero with id:")
                .append(message)
                .append(" was not found")
                .toString()
        );
    }
}
