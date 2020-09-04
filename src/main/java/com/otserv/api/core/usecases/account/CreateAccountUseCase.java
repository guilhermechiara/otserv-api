package com.otserv.api.core.usecases.account;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.domain.AccountType;
import com.otserv.api.core.exceptions.EmailOrAccountNameAlreadyInUseException;
import com.otserv.api.core.repositories.AccountRepository;
import com.otserv.api.core.repositories.PasswordRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CreateAccountUseCase implements
        UseCase<CreateAccountUseCase.InputValues, CreateAccountUseCase.OutputValues> {
    private AccountRepository accountRepository;
    private PasswordRepository passwordRepository;

    public CreateAccountUseCase(
            AccountRepository accountRepository,
            PasswordRepository passwordRepository
    ) {
        this.accountRepository = accountRepository;
        this.passwordRepository = passwordRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (this.accountRepository.existsByNameOrEmail(input.getName(), input.getEmail())) {
            throw new EmailOrAccountNameAlreadyInUseException();
        }

        Account account = Account.builder()
                .email(input.getName())
                .name(input.getName())
                .password(passwordRepository.encode(input.getPassword()))
                .premiumDays(0)
                .type(AccountType.ACCOUNT_TYPE_NORMAL)
                .creation(Instant.now())
                .lastDay(Instant.now())
                .build();

        return new OutputValues(
                this.accountRepository.save(account)
        );
    }

    @Value
    public static class InputValues {
        private String name;
        private String password;
        private String email;
    }

    @Value
    public static class OutputValues {
        private Account account;
    }
}
