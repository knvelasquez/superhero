package com.jwt.service;

import com.jwt.api.JwtApi;
import com.jwt.model.JwtModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Hs256JwtApi implements JwtApi {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final String AUTHORITIES = "authorities";
    private static final String ISSUER = "Super Heroes Fintech, S.A";
    private static final String COMPANY = "Company";
    private static final String USERNAME = "UserName";
    private static final SignatureAlgorithm SIGNATURE = SignatureAlgorithm.HS256;//SignatureAlgorithm.HS512;


    @Override
    public String create(int idUser, List<String> privileges) {
        if (privileges == null || privileges.size() == 0) {
            return null;
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put(AUTHORITIES, privileges);

        return Jwts
                .builder()
                .setIssuer(ISSUER)
                .setSubject(String.valueOf(idUser))
                .setClaims(extraClaims)
                .claim(COMPANY, ISSUER)
                .claim(USERNAME, idUser)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) //set token for 24 min
                .signWith(getSignInKey(), SIGNATURE)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public JwtModel getAllInfo(String jwt) {
        // TODO implement getAllInfo method
        return null;
    }
}
