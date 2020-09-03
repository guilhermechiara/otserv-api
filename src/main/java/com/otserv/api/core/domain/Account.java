package com.otserv.api.core.domain;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Instant;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Account {
    private Long id;
    private String name;
    private String password;
    private String secret;
    private AccountType type;
    private int premiumDays;
    private Instant lastDay;
    private String email;
    private Instant creation;
}
