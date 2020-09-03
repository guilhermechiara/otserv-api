package com.otserv.api.core.exceptions;

public class EmailOrAccountNameAlreadyInUseException extends DomainException {
    public EmailOrAccountNameAlreadyInUseException() {
        super("Email or account name is already in use");
    }
}
