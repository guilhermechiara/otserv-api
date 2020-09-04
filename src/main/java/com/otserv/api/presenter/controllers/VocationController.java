package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.vocations.GetVocationsUseCase;
import com.otserv.api.presenter.entities.vocation.VocationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/vocations")
public class VocationController {
    private GetVocationsUseCase getVocationsUseCase;

    public VocationController(GetVocationsUseCase getVocationsUseCase) {
        this.getVocationsUseCase = getVocationsUseCase;
    }

    @GetMapping
    public CompletableFuture<List<VocationResponse>> getAll() {
        return CompletableFuture
                .supplyAsync(GetVocationsUseCase.InputValues::new)
                .thenApplyAsync(getVocationsUseCase::execute)
                .thenApplyAsync(outputValues -> VocationResponse.from(outputValues.getVocations()));
    }
}
