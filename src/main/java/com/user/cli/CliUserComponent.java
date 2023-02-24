package com.user.cli;

import com.user.api.UserApi;
import com.user.repository.PrivilegeRepository;
import com.user.service.H2UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CliUserComponent {

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
        System.out.println("******************************************** Start Get all privileges *************************************************************************");

        final int idUser = 1;
        final List<String> privileges = userApi.getAllPrivileges(idUser);
        System.out.println("get all privilege for userId=" + idUser);
        System.out.println(privileges);

        final int idUser2 = 2;
        final List<String> privileges2 = userApi.getAllPrivileges(idUser2);
        System.out.println("get all privilege for userId=" + idUser2);
        System.out.println(privileges2);

        final int idUser3 = 3;
        final List<String> privileges3 = userApi.getAllPrivileges(idUser3);
        System.out.println("get all privilege for userId=" + idUser3);
        System.out.println(privileges3);

        final int idUser4 = 4;
        final List<String> privileges4 = userApi.getAllPrivileges(idUser4);
        System.out.println("get all privilege for userId=" + idUser4);
        System.out.println(privileges4);

        final int idUser5 = 5;
        final List<String> privileges5 = userApi.getAllPrivileges(idUser5);
        System.out.println("get all privilege for userId=" + idUser5);
        System.out.println(privileges5);
        System.out.println("******************************************** End Get all privileges ***************************************************************************");
    }
}
