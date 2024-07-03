package com.example.demo.service.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.model.User;
import com.example.demo.repository.UserInfoRepository;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    private static final String UPLOAD_DIR = "/path/to/upload/directory"; // アップロードディレクトリのパスを設定

    public User findByLoginId(String loginId) {
        UserInfo userInfo = userInfoRepository.findByLoginId(loginId);
        return convertToUser(userInfo);
    }

    public void updateUser(User user) {
        MultipartFile profileImage = user.getProfileImage();
        if (profileImage != null && !profileImage.isEmpty()) {
            try {
                // プロフィール画像を保存する処理
                byte[] bytes = profileImage.getBytes();
                Path path = Paths.get(UPLOAD_DIR + profileImage.getOriginalFilename());
                Files.write(path, bytes);
                // 画像のパスを設定
                user.setProfileImagePath(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        UserInfo userInfo = convertToUserInfo(user);
        userInfoRepository.save(userInfo);
    }

    private User convertToUser(UserInfo userInfo) {
        User user = new User();
        user.setLoginId(userInfo.getLoginId());
        user.setName(userInfo.getName());
        user.setEmail(userInfo.getEmail());
        user.setPassword(userInfo.getPassword());
        user.setAuthority(userInfo.getAuthority().name());
        user.setStatus(userInfo.getStatus().name());
        user.setProfileImagePath(userInfo.getProfileImage()); // 画像パスをセット
        return user;
    }

    private UserInfo convertToUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginId(user.getLoginId());
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPassword(user.getPassword());
        userInfo.setAuthority(AuthorityKind.valueOf(user.getAuthority()));
        userInfo.setStatus(UserStatusKind.valueOf(user.getStatus()));
        userInfo.setProfileImage(user.getProfileImagePath()); // 画像パスをセット
        return userInfo;
    }
}
