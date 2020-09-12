package com.otserv.api.test.core.usecases.account;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Account;
import com.otserv.api.data.converters.AccountTypeConverter;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class AccountTestUtils {
    public static Account createRandom() {
        Faker faker = new Faker();

        return Account
                .builder()
                .name(faker.bothify("###?##?##??"))
                .lastDay(faker.date().past(1, TimeUnit.DAYS).toInstant())
                .creation(Instant.now())
                .id(faker.number().randomNumber())
                .password(faker.crypto().sha1())
                .email(faker.bothify("#####???@gmail.com"))
                .premiumDays(faker.number().randomDigit())
                .type(new AccountTypeConverter().convertToEntityAttribute(faker.number().numberBetween(1, 5)))
                .secret(faker.bothify("##??#??#??##"))
                .build();
    }
}
