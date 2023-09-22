package com.superhero.lab.superhero.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SuperHeroModel {
    @Min(value = 1, message = "must be greater than zero.")
    @NotNull(message = "must not be null.")
    private Long id;
    @NotNull(message = "must not be null.")
    @NotEmpty(message = "must not be empty.")
    @Size(min = 3, max = 15, message = "must be between 3 and 15 characters")
    private String name;

    public SuperHeroModel() {
    }

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
