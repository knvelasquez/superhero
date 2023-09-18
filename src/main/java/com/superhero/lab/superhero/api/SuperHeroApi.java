package com.superhero.lab.superhero.api;

import com.superhero.lab.superhero.model.SuperHeroModel;

import java.util.List;

public interface SuperHeroApi {

    List<SuperHeroModel> getAll();

    SuperHeroModel getByUniqueId(long id);

    List<SuperHeroModel> getAllByContainingName(String name);

    SuperHeroModel createOrUpdate(SuperHeroModel superHero);

    void delete(Long idSuperHero);
}
