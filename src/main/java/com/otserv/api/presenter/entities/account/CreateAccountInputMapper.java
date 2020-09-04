package com.otserv.api.presenter.entities.account;

import com.otserv.api.core.usecases.account.CreateAccountUseCase;

public class CreateAccountInputMapper {
    public static CreateAccountUseCase.InputValues map(AccountRequest request) {
        return new CreateAccountUseCase.InputValues(
                request.getName(),
                request.getPassword(),
                request.getEmail()
        );
    }
}
