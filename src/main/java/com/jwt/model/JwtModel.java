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

    public int getIdUser() {
        return idUser;
    }

    public List<String> getPrivileges() {
        return privileges;
    }

    public String getCompany() {
        return company;
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
