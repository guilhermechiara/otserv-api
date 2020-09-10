package com.otserv.api.presenter.entities.player;

import com.otserv.api.core.domain.Player;
import com.otserv.api.presenter.entities.town.TownResponse;
import com.otserv.api.presenter.entities.vocation.VocationResponse;
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
    private int level;
    private VocationResponse vocation;
    private int health;
    private int healthMax;
    private int mana;
    private int manaMax;
    private Long experience;
    private TownResponse town;

    public static PlayerResponse from(Player player) {
        return PlayerResponse.builder()
                .id(player.getId())
                .name(player.getName())
                .accountId(player.getAccount().getId())
                .experience(player.getExperience())
                .health(player.getHealth())
                .healthMax(player.getHealthMax())
                .mana(player.getMana())
                .manaMax(player.getManaMax())
                .level(player.getLevel())
                .vocation(VocationResponse.from(player.getVocation()))
                .town(TownResponse.from(player.getTown()))
                .build();
    }

    public static List<PlayerResponse> from(List<Player> players) {
        return players
                .stream()
                .map(PlayerResponse::from)
                .collect(Collectors.toList());
    }
}
