package com.otserv.api.data.repositories;

import com.otserv.api.core.domain.Town;
import com.otserv.api.core.repositories.TownRepository;
import com.otserv.api.data.entities.TownEntity;
import com.otserv.api.data.repositories.jpa.JpaTownRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TownRepositoryImpl implements TownRepository {
    private JpaTownRepository jpaTownRepository;

    public TownRepositoryImpl(JpaTownRepository jpaTownRepository) {
        this.jpaTownRepository = jpaTownRepository;
    }

    @Override
    public Optional<Town> findById(Integer id) {
        return this.jpaTownRepository
                .findById(id)
                .map(TownEntity::to);
    }
}
