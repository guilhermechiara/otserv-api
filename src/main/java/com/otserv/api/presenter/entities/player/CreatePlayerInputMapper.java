package com.otserv.api.presenter.entities.player;

import com.otserv.api.core.usecases.players.CreatePlayerUseCase;

public class CreatePlayerInputMapper {
    public static CreatePlayerUseCase.InputValues map(PlayerRequest request, Long accountId) {
        return new CreatePlayerUseCase.InputValues(
                accountId,
                request.getName()
        );
    }
}
