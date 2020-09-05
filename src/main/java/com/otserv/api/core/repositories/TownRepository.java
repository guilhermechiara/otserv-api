package com.otserv.api.core.repositories;

import com.otserv.api.core.domain.Town;

import java.util.List;
import java.util.Optional;

public interface TownRepository {
    Optional<Town> findById(Integer id);
    List<Town> findAll();
}
