package com.otserv.api.data.repositories;

import com.otserv.api.config.FoldersConfiguration;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.repositories.VocationRepository;
import com.otserv.api.data.entities.VocationEntity;
import com.otserv.api.data.repositories.jpa.JpaVocationRepository;
import com.otserv.api.data.xml.vocation.VocationsXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class VocationRepositoryImpl implements VocationRepository {
    private static Logger logger = LoggerFactory.getLogger(VocationRepositoryImpl.class);

    private JpaVocationRepository jpaVocationRepository;
    private FoldersConfiguration foldersConfiguration;

    public VocationRepositoryImpl(
            JpaVocationRepository jpaVocationRepository,
            FoldersConfiguration foldersConfiguration
    ) {
        this.jpaVocationRepository = jpaVocationRepository;
        this.foldersConfiguration = foldersConfiguration;
    }

    @Override
    public Optional<Vocation> findById(Integer id) {
        CompletableFuture.runAsync(this::updateTableFromXml);

        return this.jpaVocationRepository
                .findById(id)
                .map(VocationEntity::to);
    }

    @Override
    public List<Vocation> findAll() {
        CompletableFuture.runAsync(this::updateTableFromXml);

        return this.jpaVocationRepository
                .findAll()
                .stream()
                .map(VocationEntity::to)
                .collect(Collectors.toList());
    }

    private void updateTableFromXml() {
        try {
            VocationsXml vocations = VocationsXml.read(foldersConfiguration);

            this.jpaVocationRepository.saveAll(
                    VocationsXml.to(vocations)
            );
        } catch(IOException ex) {
            logger.error(String.format("Failed to retrieve vocations from XML. Message: %s", ex.getMessage()));
        }
    }
}
