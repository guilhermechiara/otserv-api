package com.otserv.api.core.repositories;

import com.otserv.api.core.domain.Player;

import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> getByName(String name);
}
