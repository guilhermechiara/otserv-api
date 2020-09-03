package com.otserv.api.presenter.entities.player;

import com.otserv.api.core.domain.Player;
import com.otserv.api.presenter.entities.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class CreatePlayerOutputMapper {
    public static ResponseEntity<ApiResponse> map(Player player, HttpServletRequest http) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(http)
                .path("/players/{id}")
                .buildAndExpand(player.getId())
                .toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse(true, "Player created successfully")
        );
    }
}
