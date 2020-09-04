package com.otserv.api.data.repositories;

import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.repositories.VocationRepository;
import com.otserv.api.data.entities.VocationEntity;
import com.otserv.api.data.repositories.jpa.JpaVocationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VocationRepositoryImpl implements VocationRepository {
    private JpaVocationRepository jpaVocationRepository;

    public VocationRepositoryImpl(JpaVocationRepository jpaVocationRepository) {
        this.jpaVocationRepository = jpaVocationRepository;
    }

    @Override
    public List<Vocation> getAll() {
        return this.jpaVocationRepository
                .findAll()
                .stream()
                .map(VocationEntity::to)
                .collect(Collectors.toList());
    }
}
