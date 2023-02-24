package com.jwt.api;

import java.util.List;

public interface JwtApi {
    String create(int idUser, List<String> privileges);
}
