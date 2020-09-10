package com.otserv.api.presenter.entities.vocation;

import com.otserv.api.core.domain.Vocation;
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
public class VocationResponse {
    private int id;
    private String name;

    public static VocationResponse from(Vocation vocation) {
        return VocationResponse.builder()
                .id(vocation.getId())
                .name(vocation.getName())
                .build();
    }

    public static List<VocationResponse> from(List<Vocation> vocations) {
        return vocations
                .stream()
                .map(VocationResponse::from)
                .collect(Collectors.toList());
    }
}
