package com.otserv.api.test.core.usecases.player;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.core.usecases.player.GetPlayersByAccountIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetPlayerByAccountIdUseCaseTest {
    @InjectMocks
    private GetPlayersByAccountIdUseCase getPlayersByAccountIdUseCase;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() { MockitoAnnotations.initMocks(this); }

    @Test
    public void shouldThrowExceptionWhenNotFound() {
        Long accountId = new Faker().number().randomNumber();

        GetPlayersByAccountIdUseCase.InputValues input =
                new GetPlayersByAccountIdUseCase.InputValues(accountId);

        Mockito.doReturn(Optional.empty())
                .when(playerRepository)
                .findByAccountId(accountId);

        Assertions.assertThrows(
                NotFoundException.class,
                () -> getPlayersByAccountIdUseCase.execute(input),
                "Players with account id not found"
        );
    }

    @Test
    public void shouldReturnPlayers() {
        Long accountId = new Faker().number().randomNumber();

        GetPlayersByAccountIdUseCase.InputValues input =
                new GetPlayersByAccountIdUseCase.InputValues(accountId);

        List<Player> expected = new ArrayList<>();
        expected.add(PlayerTestUtils.createRandom());
        expected.add(PlayerTestUtils.createRandom());
        expected.add(PlayerTestUtils.createRandom());

        Mockito.doReturn(Optional.of(expected))
                .when(playerRepository)
                .findByAccountId(accountId);

        List<Player> actual = getPlayersByAccountIdUseCase.execute(input).getPlayers();

        Assertions.assertEquals(actual, expected);
    }
}
