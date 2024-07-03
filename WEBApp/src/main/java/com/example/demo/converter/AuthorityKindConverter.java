package com.example.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.constant.AuthorityKind;

@Component
public class AuthorityKindConverter implements Converter<String, AuthorityKind> {
    @Override
    public AuthorityKind convert(String source) {
        return AuthorityKind.from(source);
    }
}
