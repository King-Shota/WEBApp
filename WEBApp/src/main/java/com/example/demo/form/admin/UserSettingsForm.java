package com.example.demo.form.admin;

import com.example.demo.entity.UserInfo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserSettingsForm {

    @NotBlank
    private String loginId;

    @Email
    @Size(max = 255)
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9_-]{8,32}$")
    private String password;

    @Size(max = 255)
    private String name;

    // ゲッターとセッター
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // コンストラクタ
    public UserSettingsForm() {}

    public UserSettingsForm(UserInfo user) {
        this.loginId = user.getLoginId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
    }
}
