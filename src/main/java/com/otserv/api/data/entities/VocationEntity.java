package com.otserv.api.data.entities;

import com.otserv.api.core.domain.Vocation;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "vocations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VocationEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
