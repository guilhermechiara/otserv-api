package com.otserv.api.presenter.entities.player;

import com.otserv.api.core.domain.Player;
import com.otserv.api.presenter.entities.account.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
    private Long id;
    private String name;
    private Long accountId;

    public static PlayerResponse from(Player player) {
        return PlayerResponse.builder()
                .id(player.getId())
                .name(player.getName())
                .accountId(player.getAccount().getId())
                .build();
    }

    public static List<PlayerResponse> from(List<Player> players) {
        return players
                .stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());
    }
}
