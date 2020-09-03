package com.otserv.api.presenter.entities.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
}
