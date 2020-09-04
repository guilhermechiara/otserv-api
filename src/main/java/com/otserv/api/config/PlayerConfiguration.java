package com.otserv.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "player-config")
public class PlayerConfiguration {
    private int level;
    private Long experience;
    private int health;
    private int mana;
}
