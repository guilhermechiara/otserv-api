package com.otserv.api.test.core.usecases.vocation;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.repositories.VocationRepository;
import com.otserv.api.core.usecases.vocation.GetVocationsUseCase;
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

public class GetVocationsUseCaseTest {
    @InjectMocks
    private GetVocationsUseCase useCase;

    @Mock
    private VocationRepository repository;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
    }

    @Test
    public void shouldReturnEmptyWhenNoVocations() {
        GetVocationsUseCase.InputValues input =
                new GetVocationsUseCase.InputValues();

        Mockito.doReturn(Collections.emptyList())
                .when(repository)
                .findAll();

        List<Vocation> actual = useCase.execute(input).getVocations();

        Assertions.assertEquals(actual, Collections.emptyList());
    }

    @Test
    public void shouldReturnVocations() {
        List<Vocation> expected = new ArrayList<>();

        expected.add(VocationTestUtils.createRandom());
        expected.add(VocationTestUtils.createRandom());
        expected.add(VocationTestUtils.createRandom());

        GetVocationsUseCase.InputValues input =
                new GetVocationsUseCase.InputValues();

        Mockito.doReturn(Collections.unmodifiableList(expected))
                .when(repository)
                .findAll();

        List<Vocation> actual = useCase.execute(input).getVocations();

        Assertions.assertEquals(expected, actual);
    }
}
