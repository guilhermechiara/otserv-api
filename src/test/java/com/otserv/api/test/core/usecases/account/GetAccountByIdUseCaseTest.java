package com.otserv.api.test.core.usecases.account;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.AccountRepository;
import com.otserv.api.core.usecases.account.GetAccountByIdUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GetAccountByIdUseCaseTest {
    @InjectMocks
    private GetAccountByIdUseCase useCase;

    @Mock
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenNotFound() {
        Long id = new Faker().number().randomNumber();

        GetAccountByIdUseCase.InputValues input =
                new GetAccountByIdUseCase.InputValues(id);

        Mockito.doReturn(Optional.empty())
                .when(repository)
                .findById(id);

        Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(input),
                "Account with id not found"
        );
    }

    @Test
    public void shouldReturnAccountById() {
        Account account = AccountTestUtils.randomAccount();
        GetAccountByIdUseCase.InputValues input =
                new GetAccountByIdUseCase.InputValues(account.getId());

        Mockito.doReturn(Optional.of(account))
                .when(repository)
                .findById(account.getId());

        Account actual = useCase.execute(input).getAccount();

        Assertions.assertEquals(actual, account);
    }
}
