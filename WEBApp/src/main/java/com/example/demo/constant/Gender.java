package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("MALE", "男性"),
    FEMALE("FEMALE", "女性");

    private final String code;
    private final String displayValue;

    @Override
    public String toString() {
        return this.displayValue;
    }
}
