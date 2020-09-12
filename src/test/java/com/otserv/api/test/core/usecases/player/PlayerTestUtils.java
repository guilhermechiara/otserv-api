package com.otserv.api.test.core.usecases.player;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Player;

public class PlayerTestUtils {
    public static Player randomPlayer() {
        Faker faker = new Faker();

        return Player.builder().build();
    }
}
