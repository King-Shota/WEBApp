package com.example.demo.form.admin;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.UserStatusKind;

import lombok.Data;

@Data
public class GeneralSignupForm {

    private String loginId;

    private String email;

    private String name;

    private String password;

    private MultipartFile profileImage;  // ここを修正

    private String furigana;

    private String gender;

    private Integer age;

    private String selfIntroduction;

    private UserStatusKind status;
}
