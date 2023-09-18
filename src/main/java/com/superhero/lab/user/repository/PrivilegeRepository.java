package com.superhero.lab.user.repository;

import com.superhero.lab.user.entity.UserPrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<UserPrivilegeEntity, Long> {

    List<UserPrivilegeEntity> findByIdUser(int idUser);
}
