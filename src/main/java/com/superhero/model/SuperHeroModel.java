package com.superhero.model;

public class SuperHeroModel {
    private Long id;
    private String name;

    public SuperHeroModel(String name) {
        this.name = name;
    }

    public SuperHeroModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
