package com.jwt.model;

public class JwtRequestModel {
    private int idUser;

    public JwtRequestModel() {
    }

    public JwtRequestModel(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idUser\":" + idUser +
                '}';
    }
}
