package com.otserv.api.presenter.entities.account;

import com.otserv.api.core.domain.Account;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private String email;
    private int premiumDays;
    private LocalDateTime creation;
    private LocalDateTime lastDay;

    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .premiumDays(account.getPremiumDays())
                .creation(account.getCreation())
                .lastDay(account.getLastDay())
                .email(account.getEmail())
                .build();
    }
}
