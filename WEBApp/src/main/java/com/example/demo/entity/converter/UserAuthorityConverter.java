package com.example.demo.entity.converter;

import com.example.demo.constant.AuthorityKind;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserAuthorityConverter implements AttributeConverter<AuthorityKind, String> {

    @Override
    public String convertToDatabaseColumn(AuthorityKind authorityKind) {
        if (authorityKind == null) {
            return null;
        }
        return authorityKind.getCode();
    }

    @Override
    public AuthorityKind convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return AuthorityKind.from(value);
    }
}