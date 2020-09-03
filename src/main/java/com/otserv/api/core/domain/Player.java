package com.otserv.api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Player {
    private Long id;
    private String name;
    private PlayerGroup group;
    private Account account;
}
