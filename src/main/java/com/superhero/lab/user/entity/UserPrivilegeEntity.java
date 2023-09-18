package com.superhero.lab.user.entity;

import jakarta.persistence.*;

@Entity(name = "UserPrivilege")
@Table(name = "user_privilege")
public class UserPrivilegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "idUser", nullable = false)
    private int idUser;

    @Column(name = "idPrivilege", nullable = false)
    private String idPrivilege;

    public UserPrivilegeEntity() {

    }

    public Long getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getIdPrivilege() {
        return idPrivilege;
    }
}
