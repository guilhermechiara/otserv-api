package com.otserv.api.core.usecases.players;

import com.otserv.api.config.PlayerConfiguration;
import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.PlayerGroup;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.exceptions.PlayerNameAlreadyInUse;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.core.usecases.accounts.GetAccountByIdUseCase;
import com.otserv.api.core.usecases.vocations.GetVocationByIdUseCase;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreatePlayerUseCase implements
        UseCase<CreatePlayerUseCase.InputValues, CreatePlayerUseCase.OutputValues> {
    private static Logger logger = LoggerFactory.getLogger(CreatePlayerUseCase.class);

    private final PlayerRepository playerRepository;
    private final GetAccountByIdUseCase getAccountByIdUseCase;
    private final GetVocationByIdUseCase getVocationByIdUseCase;
    private final PlayerConfiguration playerConfiguration;

    public CreatePlayerUseCase(
            PlayerRepository playerRepository,
            GetAccountByIdUseCase getAccountByIdUseCase,
            GetVocationByIdUseCase getVocationByIdUseCase,
            PlayerConfiguration playerConfiguration
    ) {
        this.playerRepository = playerRepository;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.getVocationByIdUseCase = getVocationByIdUseCase;
        this.playerConfiguration = playerConfiguration;

    }

    @Override
    public OutputValues execute(InputValues input) {
        logger.info("Checking player name");
        if (this.playerRepository.existsByName(input.getName())) {
            throw new PlayerNameAlreadyInUse();
        }

        logger.info("Getting account by id");
        Account account = this.getAccountByIdUseCase
                .execute(new GetAccountByIdUseCase.InputValues(input.getAccountId()))
                .getAccount();

        logger.info("Getting vocation by id");
        Vocation vocation = this.getVocationByIdUseCase
                .execute(new GetVocationByIdUseCase.InputValues(input.getVocationId()))
                .getVocation();

        logger.info("Creating player");
        Player player = Player.builder()
                .account(account)
                .group(PlayerGroup.PLAYER)
                .name(input.getName())
                .conditions(new byte[0])
                .vocation(vocation)
                .level(playerConfiguration.getLevel())
                .experience(playerConfiguration.getExperience())
                .health(playerConfiguration.getHealth())
                .healthMax(playerConfiguration.getHealth())
                .mana(playerConfiguration.getMana())
                .manaMax(playerConfiguration.getMana())
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
