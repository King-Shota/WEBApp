package com.example.demo.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@Service
public class DeletedUserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> findDeletedUsers() {
        return userInfoRepository.findByDeleted(true);
    }

    public void deleteUserPermanently(String loginId) {
        userInfoRepository.deleteById(loginId);
    }

    public void restoreUser(String loginId) {
        UserInfo user = userInfoRepository.findById(loginId).orElse(null);
        if (user != null) {
            user.setDeleted(false);
            userInfoRepository.save(user);
        }
    }
    
    
    @Transactional
    public void softDeleteUser(String loginId) {
        userInfoRepository.softDeleteByLoginId(loginId);
    }
    
    
    
    
}
