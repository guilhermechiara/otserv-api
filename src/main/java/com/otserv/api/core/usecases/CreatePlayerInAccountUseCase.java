package com.otserv.api.core.usecases;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.PlayerGroup;
import com.otserv.api.core.repositories.PlayerRepository;
import lombok.Value;

public class CreatePlayerInAccountUseCase implements
        UseCase<CreatePlayerInAccountUseCase.InputValues, CreatePlayerInAccountUseCase.OutputValues> {
    private PlayerRepository playerRepository;
    private GetAccountByIdUseCase getAccountByIdUseCase;

    public CreatePlayerInAccountUseCase(
            PlayerRepository playerRepository,
            GetAccountByIdUseCase getAccountByIdUseCase
    ) {
        this.playerRepository = playerRepository;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Account account = this.getAccountByIdUseCase
                .execute(new GetAccountByIdUseCase.InputValues(input.getAccountId()))
                .getAccount();

        return new OutputValues(
            Player.builder()
                    .account(account)
                    .group(PlayerGroup.PLAYER)
                    .name(input.getName())
                    .build()
        );
    }

    @Value
    public static class InputValues {
        private Long accountId;
        private String name;
    }

    @Value
    public static class OutputValues {
        private Player player;
    }
}
