package com.superhero.repository;

import com.superhero.entity.SuperHeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHeroEntity, Long> {

    List<SuperHeroEntity> findByNameContainingIgnoreCase(String name);

}
