package com.otserv.api.data.entities;

import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "account")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "secret")
    private String secret;

    @Column(name = "type")
    private AccountType type;

    @Column(name = "premdays")
    private int premiumDays;

    @Column(name = "lastday")
    private LocalDateTime lastDay;

    @Column(name = "email")
    private String email;

    @Column(name = "creation")
    private LocalDateTime creation;

    public static AccountEntity from(Account object) {
        return AccountEntity.builder()
                .id(object.getId())
                .creation(object.getCreation())
                .email(object.getEmail())
                .lastDay(object.getLastDay())
                .name(object.getName())
                .password(object.getPassword())
                .premiumDays(object.getPremiumDays())
                .secret(object.getSecret())
                .type(object.getType())
                .build();
    }

    public static Account to(AccountEntity object) {
        return Account.builder()
                .id(object.getId())
                .creation(object.getCreation())
                .email(object.getEmail())
                .lastDay(object.getLastDay())
                .name(object.getName())
                .password(object.getPassword())
                .premiumDays(object.getPremiumDays())
                .secret(object.getSecret())
                .type(object.getType())
                .build();
    }
}
