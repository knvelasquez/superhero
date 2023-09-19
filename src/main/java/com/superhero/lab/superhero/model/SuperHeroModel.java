package com.superhero.lab.superhero.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SuperHeroModel {
    @Min(value = 1, message = "must be greater than zero.")
    private Long id;
    @NotNull(message = "must not be null.")
    @NotEmpty(message = "must not be empty.")
    private String name;

    public SuperHeroModel() {}

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

    @Override
    public String toString() {
        return "{" +
                ((id != null) ? "\"id\":" + id + "," : "") +
                "\"name\":\"" + name + "\"" +
                "}";
    }
}
