package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.CreateAccountUseCase;
import com.otserv.api.core.usecases.GetAccountByIdUseCase;
import com.otserv.api.core.usecases.GetAccountByNameUseCase;
import com.otserv.api.presenter.entities.account.AccountRequest;
import com.otserv.api.presenter.entities.account.AccountResponse;
import com.otserv.api.presenter.entities.account.CreateAccountInputMapper;
import com.otserv.api.presenter.entities.account.CreateAccountOutputMapper;
import com.otserv.api.presenter.entities.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(
            GetAccountByIdUseCase getAccountByIdUseCase,
            CreateAccountUseCase createAccountUseCase
    ) {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.createAccountUseCase = createAccountUseCase;
    }

    @GetMapping("/{id}")
    public CompletableFuture<AccountResponse> getById(@PathVariable Long id) {
        return CompletableFuture
                .supplyAsync(() -> new GetAccountByIdUseCase.InputValues(id))
                .thenApplyAsync(getAccountByIdUseCase::execute)
                .thenApplyAsync(outputValues -> AccountResponse.from(outputValues.getAccount()));
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<ApiResponse>> create(
            @RequestBody AccountRequest request,
            HttpServletRequest http
    ) {
        return CompletableFuture
                .supplyAsync(() -> CreateAccountInputMapper.map(request))
                .thenApplyAsync(createAccountUseCase::execute)
                .thenApplyAsync(outputValues -> CreateAccountOutputMapper.map(outputValues.getAccount(), http));

    }
}
