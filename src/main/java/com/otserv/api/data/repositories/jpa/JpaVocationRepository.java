package com.otserv.api.data.repositories.jpa;

import com.otserv.api.data.entities.VocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaVocationRepository extends JpaRepository<VocationEntity, Integer> {
    Optional<VocationEntity> findById(Integer id);
}
