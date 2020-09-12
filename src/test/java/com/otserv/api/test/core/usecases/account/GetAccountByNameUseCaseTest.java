package com.otserv.api.test.core.usecases.account;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.AccountRepository;
import com.otserv.api.core.usecases.account.GetAccountByNameUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GetAccountByNameUseCaseTest {
    @InjectMocks
    private GetAccountByNameUseCase useCase;

    @Mock
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldThrowExceptionWhenNotFound() {
        String name = new Faker().bothify("##?##??#");

        GetAccountByNameUseCase.InputValues input =
                new GetAccountByNameUseCase.InputValues(name);

        Mockito.doReturn(Optional.empty())
                .when(repository)
                .findByName(name);

        Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.execute(input),
                "Account with name not found"
        );
    }

    @Test
    public void shouldReturnAccountByName() {
        Account account = AccountTestUtils.randomAccount();
        GetAccountByNameUseCase.InputValues input =
                new GetAccountByNameUseCase.InputValues(account.getName());

        Mockito.doReturn(Optional.of(account))
                .when(repository)
                .findByName(account.getName());

        Account actual = useCase.execute(input).getAccount();

        Assertions.assertEquals(actual, account);
    }
}
