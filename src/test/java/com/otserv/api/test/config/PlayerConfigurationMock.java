package com.otserv.api.test.config;

import com.github.javafaker.Faker;

public class PlayerConfigurationMock {
    public static int getLevel() {
        return new Faker().number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Long getExperience() {
        return new Faker().number().randomNumber();
    }

    public static int getHealth() {
        return new Faker().number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static int getMana() {
        return new Faker().number().numberBetween(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
