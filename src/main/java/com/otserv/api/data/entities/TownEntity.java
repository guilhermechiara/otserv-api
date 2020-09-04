package com.otserv.api.data.entities;

import com.otserv.api.core.domain.Town;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "towns")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TownEntity {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "posx")
    private int posX;

    @Column(name = "posy")
    private int posY;

    @Column(name = "posz")
    private int posZ;

    public static Town to(TownEntity town) {
        return Town
                .builder()
                .id(town.getId())
                .name(town.getName())
                .posX(town.getPosX())
                .posY(town.getPosY())
                .posZ(town.getPosY())
                .build();
    }

    public static TownEntity from(Town town) {
        return TownEntity
                .builder()
                .id(town.getId())
                .name(town.getName())
                .posX(town.getPosX())
                .posY(town.getPosY())
                .posZ(town.getPosY())
                .build();
    }
}
