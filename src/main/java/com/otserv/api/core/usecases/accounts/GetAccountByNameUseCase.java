package com.otserv.api.core.usecases.accounts;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.AccountRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class GetAccountByNameUseCase implements
        UseCase<GetAccountByNameUseCase.InputValues, GetAccountByNameUseCase.OutputValues> {
    private AccountRepository accountRepository;

    public GetAccountByNameUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return this.accountRepository
            .findByName(input.getName())
            .map(OutputValues::new)
            .orElseThrow(() -> new NotFoundException("Account with name not found!"));
    }

    @Value
    public static class InputValues {
        private String name;
    }

    @Value
    public static class OutputValues {
        private Account account;
    }
}
