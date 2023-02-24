package com.jwt.cli;

import com.jwt.api.JwtApi;
import com.jwt.service.Hs256JwtApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CliJwtComponent {
    private final JwtApi jwtApi;

    @Autowired
    public CliJwtComponent() {
        this.jwtApi = new Hs256JwtApi();
        init();
    }

    private void init() {
        createNewJwt();
    }

    private void createNewJwt() {
        System.out.println("******************************************** Start Create a new Jwt ***************************************************************************");
        final int idUser = 1;
        final List<String> privileges = new ArrayList<>();
        privileges.add("ROLE_ADMIN");
        final String jwtUser1 = jwtApi.create(idUser, privileges);

        System.out.println("jwt for User=" + idUser + " and privileges=" + privileges);
        System.out.println(jwtUser1);

        final int idUser2 = 2;
        final List<String> privileges2 = new ArrayList<>();
        privileges2.add("ROLE_CONSULT");
        privileges2.add("ROLE_UPDATE");
        final String jwtUser2 = jwtApi.create(idUser2, privileges2);
        System.out.println("jwt for User=" + idUser2 + " and privileges=" + privileges2);
        System.out.println(jwtUser2);

        System.out.println("******************************************** End Create a new Jwt *****************************************************************************");
    }
}
