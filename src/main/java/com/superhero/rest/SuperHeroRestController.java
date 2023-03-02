package com.superhero.rest;

import com.exceptionhandler.SuperHeroNotFoundIdException;
import com.exceptionhandler.SuperHeroNotFoundNameException;
import com.exectime.api.ExecTime;
import com.superhero.api.SuperHeroApi;
import com.superhero.model.SuperHeroModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/")
@SecurityRequirement(name = "bearerToken")
public class SuperHeroRestController {

    private static final Logger logger = LogManager.getLogger(SuperHeroRestController.class);
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
    public SuperHeroModel getByUniqueId(@PathVariable @NonNull Long id) throws Exception {
        final SuperHeroModel superHero = superHeroApi.getByUniqueId(id);
        Optional<SuperHeroModel> checkNull = Optional.ofNullable(superHero);

        if (!checkNull.isPresent()) {
            StringBuilder msg = new StringBuilder(String.format("SuperHero with id: %s, not exist", id));
            logger.error(msg.toString());
            throw new SuperHeroNotFoundIdException(msg.toString());
        }

        return superHero;
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
        Optional<String> checkNull = Optional.ofNullable(superHero.getName());
        if (!checkNull.isPresent()) {
            StringBuilder msg = new StringBuilder(String.format("The name of the Super Hero cannot be null"));
            logger.error(msg.toString());
            throw new SuperHeroNotFoundNameException(msg.toString());
        }
        return superHeroApi.createOrUpdate(superHero);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_UPDATE')")
    @RequestMapping(value = "superhero", method = RequestMethod.PUT)
    public SuperHeroModel update(@RequestBody SuperHeroModel superHero) {
        Optional<Long> checkNotNullId = Optional.ofNullable(superHero.getId());
        Optional<String> checkNotNullName = Optional.ofNullable(superHero.getName());

        if (!checkNotNullId.isPresent()) {
            StringBuilder msg = new StringBuilder(String.format("The id of the Super Hero cannot be null"));
            logger.error(msg.toString());
            throw new SuperHeroNotFoundIdException(msg.toString());
        }
        if (!checkNotNullName.isPresent()) {
            StringBuilder msg = new StringBuilder(String.format("The name of the Super Hero cannot be null"));
            logger.error(msg.toString());
            throw new SuperHeroNotFoundNameException(msg.toString());
        }

        return superHeroApi.createOrUpdate(superHero);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DELETE')")
    @RequestMapping(value = "superhero/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        superHeroApi.delete(id);
    }
}
