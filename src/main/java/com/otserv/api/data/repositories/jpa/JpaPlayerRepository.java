package com.otserv.api.data.repositories.jpa;

import com.otserv.api.data.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaPlayerRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity> findById(Long id);
    Optional<List<PlayerEntity>> findAllByAccount_Id(Long id);
}
