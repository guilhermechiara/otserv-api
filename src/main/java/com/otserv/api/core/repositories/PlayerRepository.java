package com.otserv.api.core.repositories;

import com.otserv.api.core.domain.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> findById(Long id);
    Optional<List<Player>> findByAccountId(Long id);

    boolean existsByName(String name);
}
