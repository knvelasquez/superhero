package com.jwt.cli;

import com.jwt.api.JwtApi;
import com.jwt.model.JwtModel;
import com.jwt.service.Hs256JwtApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CliJwtComponent {
    private final JwtApi jwtApi;
    private String jwtUser1;
    private String jwtUser2;

    @Autowired
    public CliJwtComponent() {
        this.jwtApi = new Hs256JwtApi();
        init();
    }

    private void init() {
        createNewJwt();
        getAllInfoJwt();
    }

    private void getAllInfoJwt() {
        System.out.println("******************************************** Start Get all info Jwt ***************************************************************************");

        final JwtModel jwtResult = jwtApi.getAllInfo(jwtUser1);
        System.out.println("jwt info for= ..." + jwtUser1.substring(jwtUser1.length() - 50));
        System.out.println(jwtResult);

        final JwtModel jwtResult2 = jwtApi.getAllInfo(jwtUser2);
        System.out.println("jwt info for= ..." + jwtUser2.substring(jwtUser2.length() - 50));
        System.out.println(jwtResult2);

        System.out.println("******************************************** End Get all info Jwt *****************************************************************************");
    }

    private void createNewJwt() {
        System.out.println("******************************************** Start Create a new Jwt ***************************************************************************");
        final int idUser = 1;
        final List<String> privileges = new ArrayList<>();
        privileges.add("ROLE_ADMIN");
        jwtUser1 = jwtApi.create(idUser, privileges);

        System.out.println("jwt for User=" + idUser + " and privileges=" + privileges);
        System.out.println(jwtUser1);

        final int idUser2 = 2;
        final List<String> privileges2 = new ArrayList<>();
        privileges2.add("ROLE_CONSULT");
        privileges2.add("ROLE_UPDATE");
        jwtUser2 = jwtApi.create(idUser2, privileges2);
        System.out.println("jwt for User=" + idUser2 + " and privileges=" + privileges2);
        System.out.println(jwtUser2);

        System.out.println("******************************************** End Create a new Jwt *****************************************************************************");
    }
}
