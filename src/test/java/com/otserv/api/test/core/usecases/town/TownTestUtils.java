package com.otserv.api.test.core.usecases.town;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Town;

import java.util.List;

public class TownTestUtils {
    public static Town createRandom() {
        Faker faker = new Faker();

        return Town
                .builder()
                .name(faker.address().cityName())
                .posZ(faker.number().randomDigit())
                .posY(faker.number().randomDigit())
                .posZ(faker.number().randomDigit())
                .id(faker.number().randomDigit())
                .build();
    }
}
