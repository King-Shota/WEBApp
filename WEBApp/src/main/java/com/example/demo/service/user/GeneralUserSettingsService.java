package com.example.demo.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.admin.UserSettingsForm;
import com.example.demo.repository.UserInfoRepository;

public class GeneralUserSettingsService {
	
	@Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo findUserByLoginId(String loginId) {
        return userInfoRepository.findByLoginId(loginId);
    }

    public void updateUserSettings(String loginId, UserSettingsForm form) {
        UserInfo user = userInfoRepository.findByLoginId(loginId);
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setName(form.getName());
        userInfoRepository.save(user);
    }

}
