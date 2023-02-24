package com.user.service;

import com.user.entity.UserPrivilegeEntity;
import java.util.ArrayList;
import java.util.List;

public class Mapper {

    static List<String> fromEntity(List<UserPrivilegeEntity> listEntity) {
        final List<String> listResult = new ArrayList<>();
        listEntity.forEach(entity ->
                listResult.add(entity.getIdPrivilege()));
        return listResult;
    }
}
