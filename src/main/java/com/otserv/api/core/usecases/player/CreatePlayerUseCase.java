package com.otserv.api.core.usecases.player;

import com.otserv.api.config.PlayerConfiguration;
import com.otserv.api.core.UseCase;
import com.otserv.api.core.usecases.account.GetAccountByIdUseCase;
import com.otserv.api.core.usecases.town.GetTownByIdUseCase;
import com.otserv.api.core.usecases.vocation.GetVocationByIdUseCase;
import com.otserv.api.core.exceptions.PlayerNameAlreadyInUse;
import com.otserv.api.core.domain.*;
import com.otserv.api.core.repositories.PlayerRepository;
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
    private final GetTownByIdUseCase getTownByIdUseCase;

    public CreatePlayerUseCase(
            PlayerRepository playerRepository,
            GetAccountByIdUseCase getAccountByIdUseCase,
            GetVocationByIdUseCase getVocationByIdUseCase,
            PlayerConfiguration playerConfiguration,
            GetTownByIdUseCase getTownByIdUseCase
    ) {
        this.playerRepository = playerRepository;
        this.getAccountByIdUseCase = getAccountByIdUseCase;
        this.getVocationByIdUseCase = getVocationByIdUseCase;
        this.playerConfiguration = playerConfiguration;
        this.getTownByIdUseCase = getTownByIdUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        logger.info("Checking player name...");
        if (this.playerRepository.existsByName(input.getName())) {
            throw new PlayerNameAlreadyInUse();
        }

        logger.info("Getting account by id...");
        Account account = this.getAccountByIdUseCase
                .execute(new GetAccountByIdUseCase.InputValues(input.getAccountId()))
                .getAccount();

        logger.info("Getting vocation by id...");
        Vocation vocation = this.getVocationByIdUseCase
                .execute(new GetVocationByIdUseCase.InputValues(input.getVocationId()))
                .getVocation();

        Town town = this.getTownByIdUseCase
                .execute(new GetTownByIdUseCase.InputValues(input.getTownId()))
                .getTown();

        logger.info("Creating player...");
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
                .town(town)
                .build();

        return new OutputValues(
            this.playerRepository.save(player)
        );
    }

    @Value
    public static class InputValues {
        private Long accountId;
        private String name;
        private Integer vocationId;
        private Integer townId;
    }

    @Value
    public static class OutputValues {
        private Player player;
    }
}
