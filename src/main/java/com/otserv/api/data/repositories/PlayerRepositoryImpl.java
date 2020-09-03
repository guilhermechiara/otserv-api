package com.otserv.api.data.repositories;

import com.otserv.api.core.domain.Player;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.data.entities.PlayerEntity;
import com.otserv.api.data.repositories.jpa.JpaPlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {
    private JpaPlayerRepository jpaPlayerRepository;

    public PlayerRepositoryImpl(JpaPlayerRepository jpaPlayerRepository) {
        this.jpaPlayerRepository = jpaPlayerRepository;
    }

    @Override
    public Player save(Player player) {
        return null;
    }

    @Override
    public Optional<Player> getByName(String name) {
        return this.jpaPlayerRepository
                .findByName(name)
                .map(PlayerEntity::to);
    }
}
