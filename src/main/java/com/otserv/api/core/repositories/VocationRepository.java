package com.otserv.api.core.repositories;

import com.otserv.api.core.domain.Vocation;

import java.util.List;
import java.util.Optional;

public interface VocationRepository {
    List<Vocation> findAll();
    Optional<Vocation> findById(Integer id);
}
