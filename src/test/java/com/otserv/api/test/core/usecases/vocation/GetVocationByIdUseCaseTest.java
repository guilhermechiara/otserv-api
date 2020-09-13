package com.otserv.api.test.core.usecases.vocation;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Vocation;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.VocationRepository;
import com.otserv.api.core.usecases.vocation.GetVocationByIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.Optional;

public class GetVocationByIdUseCaseTest {
    @InjectMocks
    private GetVocationByIdUseCase useCase;

    @Mock
    private VocationRepository repository;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.faker = new Faker();
    }

    @Test
    public void shouldThrowNotFoundException() {
        int id = faker.number().randomDigit();

        GetVocationByIdUseCase.InputValues input =
                new GetVocationByIdUseCase.InputValues(id);

        Mockito.doReturn(Optional.empty())
                .when(repository)
                .findById(id);

        Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(input),
                "Vocation with id not found"
        );
    }

    @Test
    public void shouldReturnVocationById() {
        Vocation expected = VocationTestUtils.createRandom();

        GetVocationByIdUseCase.InputValues input =
                new GetVocationByIdUseCase.InputValues(expected.getId());

        Mockito.doReturn(Optional.of(expected))
                .when(repository)
                .findById(expected.getId());

        Vocation actual = useCase.execute(input).getVocation();

        Assertions.assertEquals(expected, actual);
    }
}
