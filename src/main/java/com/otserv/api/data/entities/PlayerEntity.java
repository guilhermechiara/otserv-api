package com.otserv.api.data.entities;

import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.PlayerGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "players")
@Getter
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

    @ManyToOne()
    private AccountEntity account;

    public static Player to(PlayerEntity player) {
        return Player.builder()
                .name(player.getName())
                .group(player.getGroup())
                .account(AccountEntity.to(player.getAccount()))
                .id(player.getId())
                .build();
    }
}
