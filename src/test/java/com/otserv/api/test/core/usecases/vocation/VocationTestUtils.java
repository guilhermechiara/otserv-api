package com.otserv.api.test.core.usecases.vocation;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Vocation;

public class VocationTestUtils {
    public static Vocation createRandom() {
        Faker faker = new Faker();

        return Vocation
                .builder()
                .name(faker.job().title())
                .id(faker.number().randomDigit())
                .build();
    }
}
