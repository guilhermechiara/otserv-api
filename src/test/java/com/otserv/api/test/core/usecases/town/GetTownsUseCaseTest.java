package com.otserv.api.test.core.usecases.town;

import com.otserv.api.core.domain.Town;
import com.otserv.api.core.repositories.TownRepository;
import com.otserv.api.core.usecases.town.GetTownsUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetTownsUseCaseTest {
    @InjectMocks
    private GetTownsUseCase useCase;

    @Mock
    private TownRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTowns() {
        List<Town> towns = new ArrayList<>();

        towns.add(TownTestUtils.createRandom());
        towns.add(TownTestUtils.createRandom());
        towns.add(TownTestUtils.createRandom());

        GetTownsUseCase.InputValues input =
                new GetTownsUseCase.InputValues();

        Mockito.doReturn(Collections.unmodifiableList(towns))
                .when(repository)
                .findAll();

        List<Town> actual = useCase.execute(input).getTowns();

        Assertions.assertIterableEquals(actual, towns);
    }

    @Test
    public void shouldReturnEmptyWhenNoTowns() {
        GetTownsUseCase.InputValues input =
                new GetTownsUseCase.InputValues();

        Mockito.doReturn(Collections.emptyList())
                .when(repository)
                .findAll();

        List<Town> actual = useCase.execute(input).getTowns();

        Assertions.assertIterableEquals(actual, Collections.emptyList());
    }
}
