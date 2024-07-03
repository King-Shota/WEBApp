package com.example.demo.service.admin;

import java.util.List;

import com.example.demo.dto.admin.UserListInfo;
import com.example.demo.form.admin.UserListForm;

public interface UserListService {

    List<UserListInfo> editUserList();

    void deleteUser(String loginId);

    List<UserListInfo> searchUsers(UserListForm form);
}
