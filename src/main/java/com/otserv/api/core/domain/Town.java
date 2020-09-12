package com.otserv.api.core.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Town {
    private Integer id;
    private String name;
    private int posX;
    private int posY;
    private int posZ;
}
