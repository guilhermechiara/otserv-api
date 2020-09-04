package com.otserv.api.data.repositories.jpa;

import com.otserv.api.data.entities.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTownRepository extends JpaRepository<TownEntity, Integer> {
    Optional<TownEntity> findById(Integer id);
}
