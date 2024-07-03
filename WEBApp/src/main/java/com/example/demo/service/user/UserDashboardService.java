package com.example.demo.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@Service
public class UserDashboardService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public int getTotalYearlyLikes(String loginId) {
        Optional<UserInfo> userOptional = userInfoRepository.findById(loginId);
        if (userOptional.isPresent()) {
            UserInfo user = userOptional.get();
            System.out.println("Yearly Likes: " + user.getLikes());  // ログ出力
            return user.getLikes(); // Assuming likes are reset yearly, otherwise, calculation logic needed
        }
        System.out.println("Yearly Likes not found for user: " + loginId);  // ログ出力
        return 0;
    }

    public int getTotalMonthlyLikes(String loginId) {
        Optional<UserInfo> userOptional = userInfoRepository.findById(loginId);
        if (userOptional.isPresent()) {
            UserInfo user = userOptional.get();
            // Assuming likes are reset monthly, otherwise, calculation logic needed
            System.out.println("Monthly Likes: " + user.getLikes());  // ログ出力
            return user.getLikes();
        }
        System.out.println("Monthly Likes not found for user: " + loginId);  // ログ出力
        return 0;
    }
}
