package com.superhero.lab.exceptionhandler;

public class SuperHeroHeaderNotFoundException extends RuntimeException {
    public SuperHeroHeaderNotFoundException() {
        super("SuperHero Header Not Found in request");
    }
}
