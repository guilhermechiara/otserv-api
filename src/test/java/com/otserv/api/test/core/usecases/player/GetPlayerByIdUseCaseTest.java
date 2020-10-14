package com.otserv.api.test.core.usecases.player;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Player;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.PlayerRepository;
import com.otserv.api.core.usecases.player.GetPlayerByIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GetPlayerByIdUseCaseTest {
    @InjectMocks
    private GetPlayerByIdUseCase getPlayerByIdUseCase;

    @Mock
    private PlayerRepository playerRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenNotFound() {
        Long id = new Faker().number().randomNumber();

        GetPlayerByIdUseCase.InputValues input =
                new GetPlayerByIdUseCase.InputValues(id);

        Mockito.doReturn(Optional.empty())
                .when(playerRepository)
                .findById(id);

        Assertions.assertThrows(
                NotFoundException.class,
                () -> getPlayerByIdUseCase.execute(input),
                "Player with id not found"
        );
    }

    @Test
    public void shouldReturnPlayerById() {
        Player expected = PlayerTestUtils.createRandom();
        GetPlayerByIdUseCase.InputValues input =
                new GetPlayerByIdUseCase.InputValues(expected.getId());

        Mockito.doReturn(Optional.of(expected))
                .when(playerRepository)
                .findById(expected.getId());

        Player actual = getPlayerByIdUseCase.execute(input).getPlayer();

        Assertions.assertEquals(expected, actual);
    }
}
