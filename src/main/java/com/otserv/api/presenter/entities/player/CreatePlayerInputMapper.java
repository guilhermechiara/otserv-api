package com.otserv.api.presenter.entities.player;

import com.otserv.api.core.usecases.player.CreatePlayerUseCase;

public class CreatePlayerInputMapper {
    public static CreatePlayerUseCase.InputValues map(PlayerRequest request, Long accountId) {
        return new CreatePlayerUseCase.InputValues(
                accountId,
                request.getName(),
                request.getVocationId(),
                request.getTownId()
        );
    }
}
