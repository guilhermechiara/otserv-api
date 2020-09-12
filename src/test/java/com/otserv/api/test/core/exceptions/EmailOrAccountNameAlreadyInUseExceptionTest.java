package com.otserv.api.test.core.exceptions;

import com.otserv.api.core.exceptions.EmailOrAccountNameAlreadyInUseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailOrAccountNameAlreadyInUseExceptionTest {
    @Test
    public void newInstancesWithCorrectMessage() {
        EmailOrAccountNameAlreadyInUseException exception = new EmailOrAccountNameAlreadyInUseException();

        Assertions.assertEquals(
                exception.getMessage(),
                "Email or account name is already in use",
                "EmailOrAccountNameAlreadyInUseException should have a message"
        );
    }
}
