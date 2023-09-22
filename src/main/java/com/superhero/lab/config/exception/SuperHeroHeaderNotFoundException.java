package com.superhero.lab.config.exception;

public class SuperHeroHeaderNotFoundException extends RuntimeException {
    public SuperHeroHeaderNotFoundException() {
        super("SuperHero Header Not Found in request");
    }
}
