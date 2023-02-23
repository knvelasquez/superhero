package com.superhero.service;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import com.superhero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class H2SuperHeroApi implements SuperHeroApi {

    private final SuperHeroRepository repository;

    @Autowired
    public H2SuperHeroApi(SuperHeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SuperHeroModel> getAll() {
        return Map.fromEntity(repository.findAll());
    }

    @Override
    public SuperHeroModel getByUniqueId(long id) {
        if (repository.existsById(id)) {
            return Map.fromEntity(repository.findById(id).get());
        }
        return null;
    }

    @Override
    public List<SuperHeroModel> getAllByContainingName(String name) {
        return Map.fromEntity(repository.findByNameContainingIgnoreCase(name));
    }

    @Override
    public SuperHeroModel createOrUpdate(SuperHeroModel superHero) {
        return Map.fromEntity(
                repository.save(Map.fromModel(superHero))
        );
    }

    @Override
    public void delete(Long idSuperHero) {
        // TODO implement delete method
    }
}
