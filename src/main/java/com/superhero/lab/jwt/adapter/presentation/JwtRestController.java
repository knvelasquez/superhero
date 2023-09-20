package com.superhero.lab.jwt.adapter.presentation;

import com.jwtlibrary.domain.JwtFactory;
import com.superhero.lab.jwt.model.JwtRequestModel;
import com.superhero.lab.user.api.UserApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class JwtRestController {
    private final JwtFactory jwtFactory;
    private static final String ISSUER = "SuperHero Issuer, S.A";
    private static final String SUBJECT = "Subject Information";
    private static final String COMPANY = "SuperHero Fintech Company, S.A";
    private final UserApi userApi;

    @Autowired
    public JwtRestController(JwtFactory jwtFactory, UserApi userApi) {
        this.jwtFactory = jwtFactory;
        this.userApi = userApi;
    }

    @RequestMapping(value = "jwt", method = RequestMethod.POST)
    public String create(@Valid @RequestBody JwtRequestModel jwtModel) {
        final int idUser = jwtModel.getIdUser();
        final List<String> privileges = userApi.getAllPrivileges(idUser);
        return jwtFactory.getEncoder().encode(ISSUER, SUBJECT, COMPANY, String.valueOf(idUser), privileges);
    }
}
