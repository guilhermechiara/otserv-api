package com.otserv.api.test.core.usecases.player;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Player;
import com.otserv.api.data.converters.PlayerGroupConverter;
import com.otserv.api.test.core.usecases.account.AccountTestUtils;
import com.otserv.api.test.core.usecases.town.TownTestUtils;
import com.otserv.api.test.core.usecases.vocation.VocationTestUtils;

public class PlayerTestUtils {
    public static Player createRandom() {
        Faker faker = new Faker();

        return Player
                .builder()
                .id(faker.number().randomNumber())
                .town(TownTestUtils.createRandom())
                .manaMax(faker.number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE))
                .mana(faker.number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE))
                .health(faker.number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE))
                .experience(faker.number().randomNumber())
                .level(faker.number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE))
                .vocation(VocationTestUtils.createRandom())
                .healthMax(faker.number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE))
                .conditions(new byte[faker.number().randomDigit()])
                .account(AccountTestUtils.createRandom())
                .group(new PlayerGroupConverter().convertToEntityAttribute(faker.number().numberBetween(1, 3)))
                .name(faker.name().fullName())
                .build();
    }
}
