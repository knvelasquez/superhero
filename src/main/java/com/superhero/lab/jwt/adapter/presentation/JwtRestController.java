package com.superhero.lab.jwt.adapter.presentation;

import com.superhero.lab.jwt.model.JwtRequestModel;
import com.jwtlibrary.adapter.factory.JwtFactory;
import com.jwtlibrary.domain.JwtEncoder;
import com.superhero.lab.user.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class JwtRestController {
    private final JwtEncoder jwtEncoder = JwtFactory.getEncoder();
    private static final String ISSUER = "SuperHero Issuer, S.A";
    private static final String SUBJECT = "Subject Information";
    private static final String COMPANY = "SuperHero Fintech Company, S.A";
    private final UserApi userApi;

    @Autowired
    public JwtRestController(UserApi userApi) {
        this.userApi = userApi;
    }

    @RequestMapping(value = "jwt", method = RequestMethod.POST)
    public String create(@RequestBody JwtRequestModel jwtModel) {
        if (jwtModel.getIdUser() == 0) {
            return null;
        }
        final int idUser = jwtModel.getIdUser();
        final List<String> privileges = userApi.getAllPrivileges(idUser);
        return jwtEncoder.encode(ISSUER, SUBJECT, COMPANY, String.valueOf(idUser), privileges);
    }
}
