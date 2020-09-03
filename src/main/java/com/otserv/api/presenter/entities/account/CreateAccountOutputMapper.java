package com.otserv.api.presenter.entities.account;

import com.otserv.api.core.domain.Account;
import com.otserv.api.presenter.entities.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class CreateAccountOutputMapper {
    public static ResponseEntity<ApiResponse> map(Account account, HttpServletRequest http) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(http)
                .path("/accounts/{id}")
                .buildAndExpand(account.getId())
                .toUri();

        return ResponseEntity.created(location).body(
                new ApiResponse(true, "Account created successfully")
        );
    }
}
