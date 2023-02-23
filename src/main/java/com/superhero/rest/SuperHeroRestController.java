package com.superhero.rest;

import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class SuperHeroRestController {

    private final SuperHeroApi superHeroApi;

    @Autowired
    public SuperHeroRestController(SuperHeroApi superHeroApi) {
        this.superHeroApi = superHeroApi;
    }

    @RequestMapping(value = "superhero", method = RequestMethod.GET)
    public List<SuperHeroModel> getAll() {
        return superHeroApi.getAll();
    }

    @RequestMapping(value = "superhero/{id}", method = RequestMethod.GET)
    public SuperHeroModel getByUniqueId(@PathVariable @NonNull Long id) {
        return superHeroApi.getByUniqueId(id);
    }

}
