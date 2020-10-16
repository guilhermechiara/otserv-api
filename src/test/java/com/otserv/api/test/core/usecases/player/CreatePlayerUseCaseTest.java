package com.otserv.api.test.core.usecases.player;

import com.github.javafaker.Faker;
import com.otserv.api.config.PlayerConfiguration;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.domain.Town;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.exceptions.PlayerNameAlreadyInUse;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.core.usecases.account.GetAccountByIdUseCase;
import com.otserv.api.core.usecases.player.CreatePlayerUseCase;
import com.otserv.api.core.usecases.town.GetTownByIdUseCase;
import com.otserv.api.core.usecases.vocation.GetVocationByIdUseCase;
import com.otserv.api.test.config.PlayerConfigurationMock;
import com.otserv.api.test.core.usecases.account.AccountTestUtils;
import com.otserv.api.test.core.usecases.town.TownTestUtils;
import com.otserv.api.test.core.usecases.vocation.VocationTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreatePlayerUseCaseTest {
    @InjectMocks
    private CreatePlayerUseCase createPlayerUseCase;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GetAccountByIdUseCase getAccountByIdUseCase;

    @Mock
    private GetTownByIdUseCase getTownByIdUseCase;

    @Mock
    private GetVocationByIdUseCase getVocationByIdUseCase;

    @Mock
    private PlayerConfiguration playerConfiguration;

    private Faker faker;
    private Account account;
    private Town town;
    private Vocation vocation;
    private CreatePlayerUseCase.InputValues input;

    @BeforeEach
    public void setUp() {
        faker = new Faker();

        account = AccountTestUtils.createRandom();
        town = TownTestUtils.createRandom();
        vocation = VocationTestUtils.createRandom();
        input = new CreatePlayerUseCase.InputValues(
                account.getId(),
                faker.name().name(),
                vocation.getId(),
                town.getId()
        );
    }

    @Test
    public void shouldThrowPlayerNameAlreadyInUseExceptionWhenNameAlreadyInUse() {
        Mockito.doReturn(true)
                .when(this.playerRepository)
                .existsByName(ArgumentMatchers.anyString());

        Assertions.assertThrows(
                PlayerNameAlreadyInUse.class,
                () -> createPlayerUseCase.execute(input),
                "Player name is already in use"
        );
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenAccountDoesNotExists() {
        Mockito.doThrow(NotFoundException.class)
                .when(getAccountByIdUseCase)
                .execute(new GetAccountByIdUseCase.InputValues(account.getId()));

        Assertions.assertThrows(
                NotFoundException.class,
                () -> createPlayerUseCase.execute(input),
                "Account with id not found"
        );
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenVocationDoesNotExists() {
        Mockito.doReturn(new GetAccountByIdUseCase.OutputValues(account))
                .when(getAccountByIdUseCase)
                .execute(new GetAccountByIdUseCase.InputValues(account.getId()));

        Mockito.doThrow(NotFoundException.class)
                .when(getVocationByIdUseCase)
                .execute(new GetVocationByIdUseCase.InputValues(vocation.getId()));

        Assertions.assertThrows(
                NotFoundException.class,
                () -> createPlayerUseCase.execute(input),
                "Vocation with id not found"
        );
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenTownDoesNotExists() {
        Mockito.doReturn(new GetAccountByIdUseCase.OutputValues(account))
                .when(getAccountByIdUseCase)
                .execute(new GetAccountByIdUseCase.InputValues(account.getId()));

        Mockito.doReturn(new GetVocationByIdUseCase.OutputValues(vocation))
                .when(getVocationByIdUseCase)
                .execute(new GetVocationByIdUseCase.InputValues(vocation.getId()));

        Mockito.doThrow(NotFoundException.class)
                .when(getTownByIdUseCase)
                .execute(new GetTownByIdUseCase.InputValues(town.getId()));

        Assertions.assertThrows(
                NotFoundException.class,
                () -> createPlayerUseCase.execute(input),
                "Town with id not found"
        );
    }

    @Test
    public void shouldCreatePlayer() {
        Player expected = PlayerTestUtils.createRandom();

        Mockito.doReturn(new GetAccountByIdUseCase.OutputValues(account))
                .when(getAccountByIdUseCase)
                .execute(new GetAccountByIdUseCase.InputValues(account.getId()));

        Mockito.doReturn(new GetVocationByIdUseCase.OutputValues(vocation))
                .when(getVocationByIdUseCase)
                .execute(new GetVocationByIdUseCase.InputValues(vocation.getId()));

        Mockito.doReturn(new GetTownByIdUseCase.OutputValues(town))
                .when(getTownByIdUseCase)
                .execute(new GetTownByIdUseCase.InputValues(town.getId()));

        Mockito.doReturn(expected)
                .when(playerRepository)
                .save(ArgumentMatchers.any(Player.class));

        Mockito.doReturn(PlayerConfigurationMock.getExperience())
                .when(playerConfiguration)
                .getExperience();

        Mockito.doReturn(PlayerConfigurationMock.getHealth())
                .when(playerConfiguration)
                .getHealth();

        Mockito.doReturn(PlayerConfigurationMock.getLevel())
                .when(playerConfiguration)
                .getLevel();

        Mockito.doReturn(PlayerConfigurationMock.getMana())
                .when(playerConfiguration)
                .getMana();

        Player actual = createPlayerUseCase.execute(input).getPlayer();

        Assertions.assertEquals(actual, expected);
    }
}
