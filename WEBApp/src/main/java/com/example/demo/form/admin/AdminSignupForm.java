package com.example.demo.form.admin;

import com.example.demo.constant.UserStatusKind;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminSignupForm {

    @NotBlank
    @Size(max = 20)  // ログインIDの長さ制限
    private String loginId;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 32)
    private String password;

    private UserStatusKind status;
}
