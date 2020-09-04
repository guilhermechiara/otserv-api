package com.otserv.api.presenter.controllers;

import com.otserv.api.core.usecases.accounts.CreateAccountUseCase;
import com.otserv.api.core.usecases.players.CreatePlayerUseCase;
import com.otserv.api.core.usecases.accounts.GetAccountByIdUseCase;
import com.otserv.api.core.usecases.players.GetPlayersByAccountIdUseCase;
import com.otserv.api.presenter.entities.account.AccountRequest;
import com.otserv.api.presenter.entities.account.AccountResponse;
import com.otserv.api.presenter.entities.account.CreateAccountInputMapper;
import com.otserv.api.presenter.entities.account.CreateAccountOutputMapper;
import com.otserv.api.presenter.entities.common.ApiResponse;
import com.otserv.api.presenter.entities.player.CreatePlayerInputMapper;
import com.otserv.api.presenter.entities.player.CreatePlayerOutputMapper;
import com.otserv.api.presenter.entities.player.PlayerRequest;
import com.otserv.api.presenter.entities.player.PlayerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final CreateAccountUseCase createAccountUseCase;
    private final CreatePlayerUseCase createPlayerUseCase;
    private final GetPlayersByAccountIdUseCase getPlayersByAccountIdUseCase;

    public AccountController(
            GetAccountByIdUseCase getAccountByIdUseCase,
            CreateAccountUseCase createAccountUseCase,
            CreatePlayerUseCase createPlayerUseCase,
            GetPlayersByAccountIdUseCase getPlayersByAccountIdUseCase
    ) {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.createAccountUseCase = createAccountUseCase;
        this.createPlayerUseCase = createPlayerUseCase;
        this.getPlayersByAccountIdUseCase = getPlayersByAccountIdUseCase;
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

    @PostMapping("/{id}/players")
    public CompletableFuture<ResponseEntity<ApiResponse>> createPlayer(
            @PathVariable Long id,
            @RequestBody @Valid PlayerRequest request,
            HttpServletRequest http
    ) {
        return CompletableFuture
                .supplyAsync(() -> CreatePlayerInputMapper.map(request, id))
                .thenApplyAsync(createPlayerUseCase::execute)
                .thenApplyAsync(outputValues -> CreatePlayerOutputMapper.map(outputValues.getPlayer(), http));
    }

    @GetMapping("/{id}/players")
    public CompletableFuture<List<PlayerResponse>> playersInAccount(@PathVariable Long id) {
        return CompletableFuture
                .supplyAsync(() -> new GetPlayersByAccountIdUseCase.InputValues(id))
                .thenApplyAsync(getPlayersByAccountIdUseCase::execute)
                .thenApplyAsync(outputValues -> PlayerResponse.from(outputValues.getPlayers()));
    }
}
