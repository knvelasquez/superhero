package com.jwt.rest;


import com.jwt.api.JwtApi;
import com.jwt.model.JwtModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class JwtRestController {
    private final JwtApi jwtApi;

    @Autowired
    public JwtRestController(JwtApi jwtApi) {
        this.jwtApi = jwtApi;
    }

    @RequestMapping(value = "jwt", method = RequestMethod.POST)
    public String create(@RequestBody JwtModel jwtModel) {
        if (jwtModel.getIdUser() == 0) {
            return null;
        }
        final int idUser = jwtModel.getIdUser();
        final List<String> privileges = new ArrayList<>();
        //TODO implement privileges
        return jwtApi.create(idUser, privileges);
    }
}
