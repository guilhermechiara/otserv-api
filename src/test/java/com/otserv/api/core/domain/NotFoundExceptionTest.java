package com.otserv.api.core.domain;

import com.otserv.api.core.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotFoundExceptionTest {
    @Test
    public void newInstancesWithCorrectMessage() {
        NotFoundException exception = new NotFoundException("Something not found");

        Assertions.assertEquals(
                "Something not found",
                exception.getMessage(),
                "NotFoundException should have a message"
        );
    }
}
