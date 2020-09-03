package com.otserv.api.data.repositories.jpa;

import com.otserv.api.data.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findByName(String name);
}
