package com.otserv.api.core.domain;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime lastDay;
    private String email;
    private LocalDateTime creation;
}
