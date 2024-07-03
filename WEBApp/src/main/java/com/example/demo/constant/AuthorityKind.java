package com.example.demo.constant;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthorityKind {

    /* 登録内容が不正 */
    UNKNOWN("", "登録内容が不正"),

    /* 一般ユーザー */
    GENERAL_USER("ROLE_USER", "一般ユーザー"),
    
    /* 管理者 */
    ADMIN("ROLE_ADMIN", "管理者");
    
    /** コード値 */
    private final String code;

    /** 画面に表示する説明文 */
    private final String displayValue;

    public static AuthorityKind from(String code) {
        return Arrays.stream(AuthorityKind.values())
            .filter(authorityKind -> authorityKind.getCode().equals(code))
            .findFirst()
            .orElse(UNKNOWN);
    }
}
