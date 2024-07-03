package com.example.demo.constant;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusKind {

    /* アカウントが無効 */
    DISABLED("DISABLED", "アクセス禁止"),

    /* アカウントが有効 */
    ENABLED("ENABLED", "アクセス許可");

    /** コード値 */
    private final String code;

    /** 画面に表示する説明文 */
    private final String displayValue;
    
    @Override
    public String toString() {
        return this.displayValue;
    }

    public static UserStatusKind from(String code) {
        return Arrays.stream(UserStatusKind.values())
            .filter(statusKind -> statusKind.getCode().equals(code))
            .findFirst()
            .orElse(DISABLED);
    }
}
