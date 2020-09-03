package com.otserv.api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlayerGroup {
    PLAYER (1),
    GAMEMASTER (2),
    GOD (3);

    @Getter
    private int value;
}
