package com.otserv.api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Town {
    private Integer id;
    private String name;
    private int posX;
    private int posY;
    private int posZ;
}
