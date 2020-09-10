package com.otserv.api.core.domain;

import com.otserv.api.core.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DomainExceptionTest {
    @Test
    public void newInstancesWithCorrectMessage() {
        DomainException exception = new DomainException("Domain Exception");

        Assertions.assertEquals(
                exception.getMessage(),
                "Domain Exception",
                "NotFoundException should have a message"
        );
    }
}
