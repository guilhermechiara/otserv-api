package com.otserv.api.data.converters;

import com.otserv.api.core.domain.PlayerGroup;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PlayerGroupConverter implements AttributeConverter<PlayerGroup, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PlayerGroup attribute) {
        return attribute.getValue();
    }

    @Override
    public PlayerGroup convertToEntityAttribute(Integer dbData) {
        return Stream.of(PlayerGroup.values())
                .filter(player -> player.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
