package com.otserv.api.data.repositories;

import com.otserv.api.core.domain.Player;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.data.entities.PlayerEntity;
import com.otserv.api.data.repositories.jpa.JpaPlayerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {
    private JpaPlayerRepository jpaPlayerRepository;

    public PlayerRepositoryImpl(JpaPlayerRepository jpaPlayerRepository) {
        this.jpaPlayerRepository = jpaPlayerRepository;
    }

    @Override
    public Player save(Player player) {
        return PlayerEntity.to(
                this.jpaPlayerRepository.save(PlayerEntity.from(player))
        );
    }

    @Override
    public Optional<Player> findById(Long id) {
        return this.jpaPlayerRepository
                .findById(id)
                .map(PlayerEntity::to);
    }

    @Override
    public Optional<List<Player>> findByAccountId(Long id) {
        return this.jpaPlayerRepository
                .findAllByAccount_Id(id)
                .map(PlayerEntity::to);
    }
}
