package com.jwt.model;

import java.util.List;

public class JwtModel {
    private int idUser;
    private List<String> privileges;
    private String company;
    private String subject;

    public JwtModel(int idUser, List<String> privileges, String company, String subject) {
        this.idUser = idUser;
        this.privileges = privileges;
        this.company = company;
        this.subject = subject;
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

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "JwtModel{" +
                "idUser=" + idUser +
                ", privileges=" + privileges +
                ", company='" + company + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
