package com.otserv.api.core.usecases.account;

import com.otserv.api.core.UseCase;
import com.otserv.api.core.domain.Account;
import com.otserv.api.core.exceptions.NotFoundException;
import com.otserv.api.core.repositories.AccountRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
public class GetAccountByIdUseCase implements
        UseCase<GetAccountByIdUseCase.InputValues, GetAccountByIdUseCase.OutputValues> {
    private AccountRepository accountRepository;

    public GetAccountByIdUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(
                this.accountRepository
                        .findById(input.getId())
                        .orElseThrow(() -> new NotFoundException("Account with id not found"))
        );
    }

    @Value
    public static class InputValues {
        private Long id;
    }

    @Value
    public static class OutputValues {
        private Account account;
    }
}
