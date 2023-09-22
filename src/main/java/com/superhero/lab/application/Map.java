package com.superhero.lab.application;

import com.superhero.lab.entity.SuperHeroEntity;
import com.superhero.lab.model.SuperHeroModel;

import java.util.List;
import java.util.stream.Collectors;

public class Map {

    static SuperHeroModel fromEntity(SuperHeroEntity entity) {
        final Long id = entity.getId();
        final String name = entity.getName();
        final SuperHeroModel model = new SuperHeroModel(id, name);
        return model;
    }

    static List<SuperHeroModel> fromEntity(List<SuperHeroEntity> listEntity) {
        final List<SuperHeroModel> listModel = listEntity.stream()
                .map(entity -> fromEntity(entity))
                .collect(Collectors.toList());

        return listModel;
    }

    static SuperHeroEntity fromModel(SuperHeroModel model) {
        final Long id = model.getId();
        final String name = model.getName();
        final SuperHeroEntity entity = new SuperHeroEntity(id, name);
        return entity;
    }
}
