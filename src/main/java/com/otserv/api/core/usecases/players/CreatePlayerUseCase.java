package com.otserv.api.core.usecases.players;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.PlayerGroup;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.exceptions.PlayerNameAlreadyInUse;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.core.usecases.accounts.GetAccountByIdUseCase;
import com.otserv.api.core.usecases.vocations.GetVocationByIdUseCase;
import com.otserv.api.core.usecases.vocations.GetVocationsUseCase;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatePlayerUseCase implements
        UseCase<CreatePlayerUseCase.InputValues, CreatePlayerUseCase.OutputValues> {
    private final PlayerRepository playerRepository;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final GetVocationByIdUseCase getVocationByIdUseCase;

    public CreatePlayerUseCase(
            PlayerRepository playerRepository,
            GetAccountByIdUseCase getAccountByIdUseCase,
            GetVocationByIdUseCase getVocationByIdUseCase
    ) {
        this.playerRepository = playerRepository;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.getVocationByIdUseCase = getVocationByIdUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (this.playerRepository.existsByName(input.getName())) {
            throw new PlayerNameAlreadyInUse();
        }

        Account account = this.getAccountByIdUseCase
                .execute(new GetAccountByIdUseCase.InputValues(input.getAccountId()))
                .getAccount();

        Vocation vocation = this.getVocationByIdUseCase
                .execute(new GetVocationByIdUseCase.InputValues(input.getVocationId()))
                .getVocation();

        Player player = Player.builder()
                .account(account)
                .group(PlayerGroup.PLAYER)
                .name(input.getName())
                .conditions(new byte[0])
                .vocation(vocation)
                .build();

        return new OutputValues(
            this.playerRepository.save(player)
        );
    }

    @Value
    public static class InputValues {
        private String name;
        private Long accountId;
        private Integer vocationId;
    }

    @Value
    public static class OutputValues {
        private Player player;
    }
}
