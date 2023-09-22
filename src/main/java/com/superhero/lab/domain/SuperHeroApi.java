package com.superhero.lab.domain;

import com.superhero.lab.model.SuperHeroModel;

import java.util.List;

public interface SuperHeroApi {

    List<SuperHeroModel> getAll();

    SuperHeroModel getByUniqueId(long id);

    List<SuperHeroModel> getAllByContainingName(String name);

    SuperHeroModel createOrUpdate(SuperHeroModel superHero);

    void delete(Long idSuperHero);
}
