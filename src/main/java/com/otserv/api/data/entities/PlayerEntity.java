package com.otserv.api.data.entities;

import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.PlayerGroup;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "players")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "group_id")
    private PlayerGroup group;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Lob
    @Column(name = "conditions")
    private byte[] conditions;

    public static Player to(PlayerEntity player) {
        return Player.builder()
                .name(player.getName())
                .group(player.getGroup())
                .account(AccountEntity.to(player.getAccount()))
                .id(player.getId())
                .build();
    }

    public static PlayerEntity from(Player player) {
        return PlayerEntity.builder()
                .account(AccountEntity.from(player.getAccount()))
                .conditions(player.getConditions())
                .group(player.getGroup())
                .id(player.getId())
                .name(player.getName())
                .build();
    }

    public static List<Player> to(List<PlayerEntity> players) {
        return players
                .stream()
                .map(PlayerEntity::to)
                .collect(Collectors.toList());
    }
}
