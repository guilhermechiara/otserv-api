package com.otserv.api.core.usecases.players;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.PlayerGroup;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.core.usecases.accounts.GetAccountByIdUseCase;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class CreatePlayerUseCase implements
        UseCase<CreatePlayerUseCase.InputValues, CreatePlayerUseCase.OutputValues> {
    private PlayerRepository playerRepository;
    private GetAccountByIdUseCase getAccountByIdUseCase;

    public CreatePlayerUseCase(
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

        Player player = Player.builder()
                .account(account)
                .group(PlayerGroup.PLAYER)
                .name(input.getName())
                .conditions(new byte[0])
                .build();

        return new OutputValues(
            this.playerRepository.save(player)
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
