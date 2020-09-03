package com.otserv.api.core.exceptions;

public class PlayerNameAlreadyInUse extends DomainException {
    public PlayerNameAlreadyInUse() {
        super("Player name is already in use");
    }
}
