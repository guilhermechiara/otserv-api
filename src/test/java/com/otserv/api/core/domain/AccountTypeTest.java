package com.otserv.api.core.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTypeTest {
    @Test
    public void playerAccountTypeShouldBeOne() {
        AccountType type = AccountType.ACCOUNT_TYPE_NORMAL;
        Assertions.assertEquals(type.getValue(), 1, "Normal accounts should have value = 1");
    }

    @Test
    public void tutorAccountTypeShouldBeTwo() {
        AccountType type = AccountType.ACCOUNT_TYPE_TUTOR;
        Assertions.assertEquals(type.getValue(), 2, "Tutor accounts should have value = 2");
    }

    @Test
    public void seniorTutorAccountTypeShouldBeThree() {
        AccountType type = AccountType.ACCOUNT_TYPE_SENIORTUTOR;
        Assertions.assertEquals(type.getValue(), 3, "Senior Tutor accounts should have value = 3");
    }

    @Test
    public void gameMasterAccountTypeShouldBeFour() {
        AccountType type = AccountType.ACCOUNT_TYPE_GAMEMASTER;
        Assertions.assertEquals(type.getValue(), 4, "GameMaster accounts should have value = 4");
    }

    @Test
    public void godAccountTypeShouldBeFive() {
        AccountType type = AccountType.ACCOUNT_TYPE_GOD;
        Assertions.assertEquals(type.getValue(), 5, "God accounts should have value = 5");
    }
}
