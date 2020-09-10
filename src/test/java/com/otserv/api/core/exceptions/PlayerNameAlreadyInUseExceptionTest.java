package com.otserv.api.core.exceptions;

import com.otserv.api.core.exceptions.PlayerNameAlreadyInUse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerNameAlreadyInUseExceptionTest {
    @Test
    public void newInstancesWithCorrectMessage() {
        PlayerNameAlreadyInUse exception = new PlayerNameAlreadyInUse();

        Assertions.assertEquals(
                exception.getMessage(),
                "Player name is already in use",
                "PlayerNameAlreadyInUse should have a message"
        );
    }
}
