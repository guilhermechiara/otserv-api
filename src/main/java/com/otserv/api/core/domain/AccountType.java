package com.otserv.api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AccountType {
    ACCOUNT_TYPE_NORMAL (1),
    ACCOUNT_TYPE_TUTOR (2),
    ACCOUNT_TYPE_SENIORTUTOR (3),
    ACCOUNT_TYPE_GAMEMASTER (4),
    ACCOUNT_TYPE_GOD (5);

    @Getter
    private int value;
}
