package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.GetPlayerByNameUseCase;
import com.otserv.api.presenter.entities.player.PlayerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private GetPlayerByNameUseCase getPlayerByNameUseCase;

    public PlayerController(GetPlayerByNameUseCase getPlayerByNameUseCase) {
        this.getPlayerByNameUseCase = getPlayerByNameUseCase;
    }

    @GetMapping("/{id}")
    public CompletableFuture<PlayerResponse> getById(@PathVariable String id) {
        return CompletableFuture
                .supplyAsync(() -> new GetPlayerByNameUseCase.InputValues(id))
                .thenApplyAsync(getPlayerByNameUseCase::execute)
                .thenApplyAsync(outputValues -> PlayerResponse.from(outputValues.getPlayer()));
    }
}
