package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.players.GetPlayerByIdUseCase;
import com.otserv.api.presenter.entities.player.PlayerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private GetPlayerByIdUseCase getPlayerByIdUseCase;

    public PlayerController(GetPlayerByIdUseCase getPlayerByIdUseCase) {
        this.getPlayerByIdUseCase = getPlayerByIdUseCase;
    }

    @GetMapping("/{id}")
    public CompletableFuture<PlayerResponse> findById(@PathVariable Long id) {
        return CompletableFuture
                .supplyAsync(() -> new GetPlayerByIdUseCase.InputValues(id))
                .thenApplyAsync(getPlayerByIdUseCase::execute)
                .thenApplyAsync(outputValues -> PlayerResponse.from(outputValues.getPlayer()));
    }
}
