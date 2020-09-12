package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.town.GetTownsUseCase;
import com.otserv.api.presenter.entities.town.TownResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/towns")
public class TownController {
    private GetTownsUseCase getTownsUseCase;

    public TownController(GetTownsUseCase getTownsUseCase) {
        this.getTownsUseCase = getTownsUseCase;
    }

    @GetMapping
    public CompletableFuture<List<TownResponse>> getAll() {
        return CompletableFuture
                .supplyAsync(() -> new GetTownsUseCase.InputValues())
                .thenApplyAsync(getTownsUseCase::execute)
                .thenApplyAsync(outputValues -> TownResponse.from(outputValues.getTowns()));
    }
}
