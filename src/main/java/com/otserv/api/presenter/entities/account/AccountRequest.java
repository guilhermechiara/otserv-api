package com.otserv.api.presenter.entities.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {
    private String name;
    private String password;
    private String secret;
    private String email;
}
