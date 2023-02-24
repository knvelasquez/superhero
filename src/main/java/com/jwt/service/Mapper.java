package com.jwt.service;

import com.jwt.model.JwtModel;
import io.jsonwebtoken.Claims;
import java.util.List;

public class Mapper {

    static JwtModel fromClaims(Claims claims) {
        final int idUser = (int) claims.get("UserName");
        final List<String> privileges = (List<String>) claims.get("authorities");
        final String company = (String) claims.get("Company");
        final String subject = claims.getSubject();
        return new JwtModel(idUser, privileges, company, subject);
    }
}
