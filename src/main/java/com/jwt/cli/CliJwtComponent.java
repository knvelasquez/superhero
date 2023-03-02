package com.jwt.cli;

import com.jwt.api.JwtApi;
import com.jwt.model.JwtModel;
import com.jwt.service.Hs256JwtApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CliJwtComponent {
    private static final Logger logger = LogManager.getLogger(CliJwtComponent.class);
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
        logger.info("******************************************** Start Get all info Jwt ***************************************************************************");

        final JwtModel jwtResult = jwtApi.getAllInfo(jwtUser1);
        logger.info("jwt info for= ..." + jwtUser1.substring(jwtUser1.length() - 50));
        logger.info(jwtResult);

        final JwtModel jwtResult2 = jwtApi.getAllInfo(jwtUser2);
        logger.info("jwt info for= ..." + jwtUser2.substring(jwtUser2.length() - 50));
        logger.info(jwtResult2);

        logger.info("******************************************** End Get all info Jwt *****************************************************************************");
    }

    private void createNewJwt() {
        logger.info("******************************************** Start Create a new Jwt ***************************************************************************");
        final int idUser = 1;
        final List<String> privileges = new ArrayList<>();
        privileges.add("ROLE_ADMIN");
        jwtUser1 = jwtApi.create(idUser, privileges);

        logger.info("jwt for User=" + idUser + " and privileges=" + privileges);
        logger.info(jwtUser1);

        final int idUser2 = 2;
        final List<String> privileges2 = new ArrayList<>();
        privileges2.add("ROLE_CONSULT");
        privileges2.add("ROLE_UPDATE");
        jwtUser2 = jwtApi.create(idUser2, privileges2);
        logger.info("jwt for User=" + idUser2 + " and privileges=" + privileges2);
        logger.info(jwtUser2);

        logger.info("******************************************** End Create a new Jwt *****************************************************************************");
    }
}
