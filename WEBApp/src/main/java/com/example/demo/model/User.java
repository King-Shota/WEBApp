package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String loginId;
    private String name;
    private String email;
    private String password;
    private String authority;
    private String status;
    private MultipartFile profileImage;
    private String furigana;
    private String gender; // 追加されていますか？
    private Integer age; // 必要であれば追加
    private String bio; // 必要であれば追加
    private String profileImagePath; // 追加
}
