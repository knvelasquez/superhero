package com.jwt.rest;

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

}
