package com.otserv.api.data.entities;

import com.otserv.api.core.domain.Vocation;
import lombok.*;

import javax.persistence.*;

@Entity(name = "vocations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocationEntity {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    public static VocationEntity from(Vocation vocation) {
        return VocationEntity.builder()
                .id(vocation.getId())
                .name(vocation.getName())
                .build();
    }

    public static Vocation to(VocationEntity vocation) {
        return Vocation.builder()
                .id(vocation.getId())
                .name(vocation.getName())
                .build();
    }
}
