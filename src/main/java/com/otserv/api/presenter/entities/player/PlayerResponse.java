package com.otserv.api.presenter.entities.player;

import com.otserv.api.core.domain.Player;
import com.otserv.api.presenter.entities.account.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
    private Long id;
    private String name;
    private AccountResponse account;

    public static PlayerResponse from(Player player) {
        return PlayerResponse.builder()
                .account(AccountResponse.from(player.getAccount()))
                .id(player.getId())
                .name(player.getName())
                .build();
    }
}
