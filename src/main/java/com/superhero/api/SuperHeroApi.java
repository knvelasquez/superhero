package com.superhero.api;

import com.superhero.model.SuperHeroModel;

import java.util.List;

public interface SuperHeroApi {

    List<SuperHeroModel> getAll();

    SuperHeroModel getByUniqueId(long id);

    List<SuperHeroModel> getAllByContainingName(String name);

    SuperHeroModel createOrUpdate(SuperHeroModel createdHero);

    void delete(Long idSuperHero);
}
