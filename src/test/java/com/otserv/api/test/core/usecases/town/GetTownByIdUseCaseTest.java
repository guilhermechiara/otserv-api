package com.otserv.api.test.core.usecases.town;

import com.otserv.api.core.domain.Town;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.TownRepository;
import com.otserv.api.core.usecases.town.GetTownByIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GetTownByIdUseCaseTest {
    @InjectMocks
    private GetTownByIdUseCase useCase;

    @Mock
    private TownRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowNotFoundException() {
        Town town = TownTestUtils.createRandom();

        GetTownByIdUseCase.InputValues input =
                new GetTownByIdUseCase.InputValues(town.getId());

        Mockito.doReturn(Optional.empty())
                .when(repository)
                .findById(town.getId());

        Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(input),
                "Town with id not found"
        );
    }

    @Test
    public void shouldReturnTownById() {
        Town expected = TownTestUtils.createRandom();

        GetTownByIdUseCase.InputValues input =
                new GetTownByIdUseCase.InputValues(expected.getId());

        Mockito.doReturn(Optional.of(expected))
                .when(repository)
                .findById(expected.getId());

        Town actual = useCase.execute(input).getTown();

        Assertions.assertEquals(expected, actual);
    }
}
