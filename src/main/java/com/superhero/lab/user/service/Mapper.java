package com.superhero.lab.user.service;

import com.superhero.lab.user.entity.UserPrivilegeEntity;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    static List<String> fromEntity(List<UserPrivilegeEntity> listEntity) {
        final List<String> listResult = listEntity.stream()
                .map(entity -> entity.getIdPrivilege())
                .collect(Collectors.toList());
        return listResult;
    }
}
