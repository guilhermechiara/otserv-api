package com.otserv.api.test.core.usecases.account;

import com.github.javafaker.Faker;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.exceptions.EmailOrAccountNameAlreadyInUseException;
import com.otserv.api.core.repositories.AccountRepository;
import com.otserv.api.core.repositories.PasswordRepository;
import com.otserv.api.core.usecases.account.CreateAccountUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class CreateAccountUseCaseTest {
    @InjectMocks
    private CreateAccountUseCase useCase;

    @Mock
    private AccountRepository repository;

    @Mock
    private PasswordRepository passwordRepository;

    private Faker faker = new Faker();

    @BeforeEach
    public void setUp() { MockitoAnnotations.initMocks(this); }

    @Test
    public void shouldThrowEmailOrAccountNameAlreadyInUseException() {
        CreateAccountUseCase.InputValues input =
                new CreateAccountUseCase.InputValues(
                        this.faker.bothify("#?#???###"),
                        this.faker.bothify("##????###?##?#???##"),
                        this.faker.bothify("##??##??##@gmail.com")
                );

        Mockito.doReturn(true)
                .when(repository)
                .existsByNameOrEmail(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        Assertions.assertThrows(EmailOrAccountNameAlreadyInUseException.class, () -> useCase.execute(input));
    }

    @Test
    public void shouldCreateAndReturnAccount() {
        CreateAccountUseCase.InputValues input =
                new CreateAccountUseCase.InputValues(
                        this.faker.bothify("#?#???###"),
                        this.faker.bothify("##????###?##?#???##"),
                        this.faker.bothify("##??##??##@gmail.com")
                );

        Account expected = AccountTestUtils.createRandom();

        Mockito.doReturn(this.faker.crypto().sha1())
                .when(passwordRepository)
                .encode(ArgumentMatchers.anyString());

        Mockito.doReturn(expected)
                .when(repository)
                .save(ArgumentMatchers.any(Account.class));

        Account actual = useCase.execute(input).getAccount();

        Assertions.assertEquals(expected, actual);
    }
}
