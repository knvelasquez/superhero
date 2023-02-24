package com.jwt.model;

import java.util.List;

public class JwtModel {
    private int idUser;
    private List<String> privileges;
    private String company;

    public JwtModel(int idUser, List<String> privileges, String company) {
        this.idUser = idUser;
        this.privileges = privileges;
        this.company = company;
    }

    @Override
    public String toString() {
        return "JwtModel{" +
                "idUser=" + idUser +
                ", privileges=" + privileges +
                ", company='" + company + '\'' +
                '}';
    }
}
