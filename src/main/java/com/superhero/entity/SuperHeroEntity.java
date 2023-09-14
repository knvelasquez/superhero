package com.superhero.entity;

import jakarta.persistence.*;

@Entity(name = "SuperHero")
@Table(name = "super_hero")
public class SuperHeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public SuperHeroEntity() {
    }

    public SuperHeroEntity(Long id, String name) {
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
