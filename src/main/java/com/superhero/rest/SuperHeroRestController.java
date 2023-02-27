package com.superhero.rest;

import com.exectime.api.ExecTime;
import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
@SecurityRequirement(name = "bearerToken")
public class SuperHeroRestController {

    private final SuperHeroApi superHeroApi;

    @Autowired
    public SuperHeroRestController(SuperHeroApi superHeroApi) {
        this.superHeroApi = superHeroApi;
    }

    @RequestMapping(value = "superhero", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CONSULT')")
    @ExecTime
    public List<SuperHeroModel> getAll() {
        return superHeroApi.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CONSULT')")
    @RequestMapping(value = "superhero/{id}", method = RequestMethod.GET)
    @ExecTime
    public SuperHeroModel getByUniqueId(@PathVariable @NonNull Long id) {
        return superHeroApi.getByUniqueId(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CONSULT')")
    @RequestMapping(value = "superhero/contain/{name}", method = RequestMethod.GET)
    @ExecTime
    public List<SuperHeroModel> getAllByContainingName(@PathVariable @NonNull String name) {
        return superHeroApi.getAllByContainingName(name);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CREATE')")
    @RequestMapping(value = "superhero", method = RequestMethod.POST)
    public SuperHeroModel create(@RequestBody SuperHeroModel superHero) {
        return superHeroApi.createOrUpdate(superHero);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_UPDATE')")
    @RequestMapping(value = "superhero", method = RequestMethod.PUT)
    public SuperHeroModel update(@RequestBody SuperHeroModel superHero) {
        return superHeroApi.createOrUpdate(superHero);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DELETE')")
    @RequestMapping(value = "superhero/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        superHeroApi.delete(id);
    }
}
