package com.jwt;

import java.util.ArrayList;
import java.util.List;
import com.jwt.model.JwtModel;

public class Mock {
    static final String jwtUser1 = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sIkNvbXBhbnkiOiJTdXBlciBIZXJvZXMgRmludGVjaCwgUy5BIiwiVXNlck5hbWUiOjEsImlhdCI6MTY3NzAzNTgwMSwiZXhwIjoxNjc3MDM3MjQxfQ.QSTkZoSQaeC01yawS4D7FKr7A-NcZoUhFzA8l9Q9DrE";
    static final int idUser1 = 1;

    static List<String> roleAdminPrivilege() {
        List<String> listResult = new ArrayList<>();
        listResult.add("ROLE_ADMIN");
        return listResult;
    }

    static JwtModel allInfo() {
        return new JwtModel();
    }
}
