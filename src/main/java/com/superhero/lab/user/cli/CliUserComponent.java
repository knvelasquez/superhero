package com.superhero.lab.user.cli;

import com.superhero.lab.user.api.UserApi;
import com.superhero.lab.user.repository.PrivilegeRepository;
import com.superhero.lab.user.service.H2UserApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Component
public class CliUserComponent {

    private static final Logger logger = LogManager.getLogger(CliUserComponent.class);
    private final UserApi userApi;

    @Autowired
    public CliUserComponent(PrivilegeRepository privilegeRepository) {
        this.userApi = new H2UserApi(privilegeRepository);
        init();
    }

    private void init() {
        getAllPrivileges();
    }

    private void getAllPrivileges() {
        logger.info("******************************************** Start Get all privileges *************************************************************************");

        final int idUser = 1;
        final List<String> privileges = userApi.getAllPrivileges(idUser);
        logger.info("get all privilege for userId=" + idUser);
        logger.info(privileges);

        final int idUser2 = 2;
        final List<String> privileges2 = userApi.getAllPrivileges(idUser2);
        logger.info("get all privilege for userId=" + idUser2);
        logger.info(privileges2);

        final int idUser3 = 3;
        final List<String> privileges3 = userApi.getAllPrivileges(idUser3);
        logger.info("get all privilege for userId=" + idUser3);
        logger.info(privileges3);

        final int idUser4 = 4;
        final List<String> privileges4 = userApi.getAllPrivileges(idUser4);
        logger.info("get all privilege for userId=" + idUser4);
        logger.info(privileges4);

        final int idUser5 = 5;
        final List<String> privileges5 = userApi.getAllPrivileges(idUser5);
        logger.info("get all privilege for userId=" + idUser5);
        logger.info(privileges5);
        logger.info("******************************************** End Get all privileges ***************************************************************************");
    }
}
