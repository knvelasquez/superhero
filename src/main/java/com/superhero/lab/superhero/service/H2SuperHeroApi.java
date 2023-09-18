package com.superhero.lab.superhero.service;

import com.superhero.lab.superhero.model.SuperHeroModel;
import com.superhero.lab.superhero.repository.SuperHeroRepository;
import com.superhero.lab.superhero.api.SuperHeroApi;
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
        if (repository.existsById(idSuperHero)) {
            repository.deleteById(idSuperHero);
        }
    }
}
