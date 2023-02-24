package com.jwt.api;

import java.util.List;
import com.jwt.model.JwtModel;

public interface JwtApi {
    String create(int idUser, List<String> privileges);

    JwtModel getAllInfo(String jwt);
}
