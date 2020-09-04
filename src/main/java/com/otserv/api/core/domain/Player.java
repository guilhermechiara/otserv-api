package com.otserv.api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Player {
    private Long id;
    private String name;
    private PlayerGroup group;
    private Account account;
    private byte[] conditions;
    private int level;
    private Vocation vocation;
    private int health;
    private int healthMax;
    private int mana;
    private int manaMax;
    private Long experience;
    private Town town;
}
