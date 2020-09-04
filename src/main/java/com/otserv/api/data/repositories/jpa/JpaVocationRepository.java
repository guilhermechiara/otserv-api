package com.otserv.api.data.repositories.jpa;

import com.otserv.api.data.entities.VocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVocationRepository extends JpaRepository<VocationEntity, Long> { }
