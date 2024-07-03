package com.example.demo.form.admin;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    private String loginId;

    @NotEmpty(message = "名前は必須項目です")
    @Size(max = 255, message = "名前は255文字以内で入力してください")
    private String name;

    @NotEmpty(message = "メールアドレスは必須項目です")
    @Email(message = "メールアドレスの形式が正しくありません")
    @Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
    private String email;

    @NotEmpty(message = "パスワードは必須項目です")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{8,32}$", message = "パスワードは8〜32文字の半角英数字と_-のみ許可されます")
    private String password;

    private AuthorityKind authority;

    private UserStatusKind status;

    private MultipartFile profileImage;

    @Size(max = 255, message = "ふりがなは255文字以内で入力してください")
    @Pattern(regexp = "^[ぁ-ん]+$", message = "ふりがなはひらがなのみで入力してください")
    private String furigana;

    private String gender;

    @Max(value = 999, message = "年齢は3桁まで許可されます")
    private Integer age;

    @Size(max = 1500, message = "自己紹介は1500文字以内で入力してください")
    private String bio;
}
