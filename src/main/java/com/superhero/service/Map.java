package com.superhero.service;

import com.superhero.entity.SuperHeroEntity;
import com.superhero.model.SuperHeroModel;

import java.util.ArrayList;
import java.util.List;

public class Map {

    static SuperHeroModel fromEntity(SuperHeroEntity entity) {
        final Long id = entity.getId();
        final String name = entity.getName();
        final SuperHeroModel model = new SuperHeroModel(id, name);
        return model;
    }

    static List<SuperHeroModel> fromEntity(List<SuperHeroEntity> listEntity) {
        List<SuperHeroModel> listModel = new ArrayList<>();

        listEntity.forEach((entity)
                -> listModel.add(fromEntity(entity)));

        return listModel;
    }

    static SuperHeroEntity fromModel(SuperHeroModel model) {
        final Long id = model.getId();
        final String name = model.getName();
        final SuperHeroEntity entity = new SuperHeroEntity(id, name);
        return entity;
    }
}
