package com.example.demo.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.UserSettingsForm;
import com.example.demo.repository.UserInfoRepository;

@Service
public class UserSettingsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo findUserByLoginId(String loginId) {
        return userInfoRepository.findById(loginId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateUserSettings(String loginId, UserSettingsForm userSettingsForm) {
        UserInfo user = findUserByLoginId(loginId);
        user.setEmail(userSettingsForm.getEmail());
        // パスワードが空でない場合のみエンコードして設定
        if (!userSettingsForm.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userSettingsForm.getPassword()));
        }
        user.setName(userSettingsForm.getName());
        userInfoRepository.save(user);
    }
}
