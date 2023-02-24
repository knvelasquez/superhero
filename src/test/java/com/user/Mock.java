package com.user;

import java.util.ArrayList;
import java.util.List;

public class Mock {

    static final String ROLE_ADMIN = "ROLE_ADMIN";
    static final String ROLE_CONSULT = "ROLE_CONSULT";
    static final String ROLE_CREATE = "ROLE_CREATE";
    static final String ROLE_UPDATE = "ROLE_UPDATE";
    static final String ROLE_DELETE = "ROLE_DELETE";

    static List<String> getPrivilegeAdmin() {
        final List<String> privileges = new ArrayList<>();
        privileges.add(ROLE_ADMIN);
        return privileges;
    }

    static List<String> getPrivilegeConsult() {
        final List<String> privileges = new ArrayList<>();
        privileges.add(ROLE_CONSULT);
        return privileges;
    }

    static List<String> getPrivilegeCreate() {
        final List<String> privileges = new ArrayList<>();
        privileges.add(ROLE_CREATE);
        return privileges;
    }

    static List<String> getPrivilegeUpdate() {
        final List<String> privileges = new ArrayList<>();
        privileges.add(ROLE_UPDATE);
        return privileges;
    }

    static List<String> getPrivilegeDelete() {
        final List<String> privileges = new ArrayList<>();
        privileges.add(ROLE_DELETE);
        return privileges;
    }
}
