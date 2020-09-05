package com.otserv.api.presenter.entities.town;

import com.otserv.api.core.domain.Town;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TownResponse {
    private Integer id;
    private String name;
    private int posX;
    private int posY;
    private int posZ;

    public static TownResponse from(Town town) {
        return TownResponse
                .builder()
                .id(town.getId())
                .name(town.getName())
                .posX(town.getPosX())
                .posY(town.getPosY())
                .posZ(town.getPosY())
                .build();
    }

    public static List<TownResponse> from(List<Town> towns) {
        return towns
                .stream()
                .map(TownResponse::from)
                .collect(Collectors.toList());
    }
}
