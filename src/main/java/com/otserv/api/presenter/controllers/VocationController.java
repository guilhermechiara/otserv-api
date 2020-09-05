package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.vocation.GetVocationsUseCase;
import com.otserv.api.presenter.entities.vocation.VocationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/vocations")
public class VocationController {
    private static final Logger logger = LoggerFactory.getLogger(VocationController.class);

    private final GetVocationsUseCase getVocationsUseCase;

    public VocationController(GetVocationsUseCase getVocationsUseCase) {
        this.getVocationsUseCase = getVocationsUseCase;
    }

    @GetMapping
    public CompletableFuture<List<VocationResponse>> findAll() {
        logger.info("Retrieving all vocations...");

        return CompletableFuture
                .supplyAsync(GetVocationsUseCase.InputValues::new)
                .thenApplyAsync(getVocationsUseCase::execute)
                .thenApplyAsync(outputValues -> VocationResponse.from(outputValues.getVocations()));
    }
}
