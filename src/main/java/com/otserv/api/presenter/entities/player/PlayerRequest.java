package com.otserv.api.presenter.entities.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class PlayerRequest {
    @NotNull
    private String name;
}
