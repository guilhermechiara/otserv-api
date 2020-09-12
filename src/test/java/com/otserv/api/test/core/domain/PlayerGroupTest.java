package com.otserv.api.test.core.domain;

import com.otserv.api.core.domain.PlayerGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerGroupTest {
    @Test
    public void playerGroupShouldBeOne() {
        PlayerGroup group = PlayerGroup.PLAYER;
        Assertions.assertEquals(group.getValue(), 1, "Regular players group should have value = 1");
    }

    @Test
    public void gameMasterGroupShouldBeTwo() {
        PlayerGroup group = PlayerGroup.GAMEMASTER;
        Assertions.assertEquals(group.getValue(), 2, "Gamemaster group should have value = 1");
    }

    @Test
    public void godGroupShouldBeThree() {
        PlayerGroup group = PlayerGroup.GOD;
        Assertions.assertEquals(group.getValue(), 3, "God group should have value = 1");
    }
}
