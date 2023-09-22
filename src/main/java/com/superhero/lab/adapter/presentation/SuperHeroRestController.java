package com.superhero.lab.adapter.presentation;

import com.jwtlibrary.domain.JwtFactory;
import com.superhero.lab.config.exception.SuperHeroNotFoundException;
import com.superhero.lab.exectime.api.ExecTime;
import com.superhero.lab.domain.SuperHeroApi;
import com.superhero.lab.model.SuperHeroModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/")
@SecurityRequirement(name = "bearerToken")
public class SuperHeroRestController {
    private static final Logger logger = LogManager.getLogger(SuperHeroRestController.class);
    private final JwtFactory jwtFactory;
    private final SuperHeroApi superHeroApi;

    @Autowired
    public SuperHeroRestController(JwtFactory jwtFactory, SuperHeroApi superHeroApi) {
        this.jwtFactory = jwtFactory;
        this.superHeroApi = superHeroApi;
    }

    @GetMapping(value = "/health")
    public ResponseEntity<Map<String, String>> healthCheck(
            //@RequestHeader(value = "Authorization") String authorization
    ) {
        Map<String, String> response = new HashMap<>();
        response.put("version", "v3.0");
        response.put("status", "healthy");
        response.put("details", "superhero service is up and running.");
        response.put("date", new Date(System.currentTimeMillis()).toString());
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "superhero", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CONSULT')")
    @Operation(summary = "get a list with all superheroes info")
    @Parameter(name = "SUPERHERO", description = "please indicate any value", in = ParameterIn.HEADER)
    @ExecTime
    public List<SuperHeroModel> getAll() {
        return superHeroApi.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CONSULT')")
    @RequestMapping(value = "superhero/{id}", method = RequestMethod.GET)
    @Operation(summary = "get a superhero info by id")
    @Parameter(name = "SUPERHERO", description = "please indicate any value", in = ParameterIn.HEADER)
    public SuperHeroModel getByUniqueId(@PathVariable @NonNull Long id) throws SuperHeroNotFoundException {
        final SuperHeroModel superHero = superHeroApi.getByUniqueId(id);
        Optional<SuperHeroModel> checkNull = Optional.ofNullable(superHero);

        if (!checkNull.isPresent()) {
            throw new SuperHeroNotFoundException(String.valueOf(id));
        }

        return superHero;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CONSULT')")
    @RequestMapping(value = "superhero/contain/{name}", method = RequestMethod.GET)
    @Operation(summary = "get a list of superheroes info by containing indicated name")
    @Parameter(name = "SUPERHERO", description = "please indicate any value", in = ParameterIn.HEADER)
    @ExecTime
    public List<SuperHeroModel> getAllByContainingName(@PathVariable @NonNull String name) {
        return superHeroApi.getAllByContainingName(name);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_CREATE')")
    @RequestMapping(value = "superhero", method = RequestMethod.POST)
    @Operation(summary = "create a superhero")
    @Parameter(name = "SUPERHERO", description = "please indicate any value", in = ParameterIn.HEADER)
    public SuperHeroModel create(@Valid @RequestBody SuperHeroModel superHero,
                                 @RequestHeader(value = "SUPERHERO") String superhero) {
        return superHeroApi.createOrUpdate(superHero);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_UPDATE')")
    @RequestMapping(value = "superhero", method = RequestMethod.PUT)
    @Operation(summary = "update a superhero info")
    @Parameter(name = "SUPERHERO", description = "please indicate any value", in = ParameterIn.HEADER)
    public SuperHeroModel update(@Valid @RequestBody SuperHeroModel superHero) {
        return superHeroApi.createOrUpdate(superHero);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_DELETE')")
    @RequestMapping(value = "superhero/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "delete a superhero info")
    @Parameter(name = "SUPERHERO", description = "please indicate any value", in = ParameterIn.HEADER)
    public void delete(@Valid @PathVariable Long id) {
        superHeroApi.delete(id);
    }
}
