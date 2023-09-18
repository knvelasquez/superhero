package com.superhero.lab.user.service;

import com.superhero.lab.user.api.UserApi;
import com.superhero.lab.user.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class H2UserApi implements UserApi {

    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public H2UserApi(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }


    @Override
    public List<String> getAllPrivileges(int idUser) {
        return Mapper.fromEntity(privilegeRepository.findByIdUser(idUser));
    }
}
