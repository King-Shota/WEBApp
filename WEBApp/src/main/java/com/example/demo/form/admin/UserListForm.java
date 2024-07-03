package com.example.demo.form.admin;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

@Data
public class UserListForm {
    private String loginId;
    private UserStatusKind userStatusKind;
    private AuthorityKind authorityKind;
}
