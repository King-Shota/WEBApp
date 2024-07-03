package com.example.demo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.constant.UserStatusKind;

@Component
public class UserStatusKindConverter implements Converter<String, UserStatusKind> {
    @Override
    public UserStatusKind convert(String source) {
        return UserStatusKind.from(source);
    }
}
