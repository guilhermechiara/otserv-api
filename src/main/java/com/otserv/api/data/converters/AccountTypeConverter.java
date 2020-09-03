package com.otserv.api.data.converters;

import com.otserv.api.core.domain.AccountType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AccountTypeConverter implements AttributeConverter<AccountType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AccountType attribute) {
        return attribute.getValue();
    }

    @Override
    public AccountType convertToEntityAttribute(Integer dbData) {
        return Stream.of(AccountType.values())
                .filter(account -> account.getValue() == dbData)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
