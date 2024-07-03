package com.example.demo.service.publicpage;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserLikes;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserLikesRepository;

@Service
public class PublicPageService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserLikesRepository userLikesRepository;

    public int incrementLikes(String loginId) {
        UserInfo user = userInfoRepository.findByLoginId(loginId);
        user.setLikes(user.getLikes() + 1);
        userInfoRepository.save(user);

        UserLikes userLike = new UserLikes();
        userLike.setUser(user);
        userLike.setLikedAt(LocalDateTime.now());
        userLikesRepository.save(userLike);

        return user.getLikes();
    }

    public int getTotalYearlyLikes(String loginId) {
        UserInfo user = userInfoRepository.findByLoginId(loginId);
        LocalDateTime startOfYear = Year.now().atDay(1).atStartOfDay();
        LocalDateTime endOfYear = Year.now().atMonth(12).atEndOfMonth().atTime(23, 59, 59);
        return userLikesRepository.countLikesBetween(user, startOfYear, endOfYear);
    }

    public int getTotalMonthlyLikes(String loginId) {
        UserInfo user = userInfoRepository.findByLoginId(loginId);
        LocalDateTime startOfMonth = YearMonth.now().atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = YearMonth.now().atEndOfMonth().atTime(23, 59, 59);
        return userLikesRepository.countLikesBetween(user, startOfMonth, endOfMonth);
    }

    public List<UserInfo> findAllUsersWithRoleUser() {
        return userInfoRepository.findAllByAuthority(AuthorityKind.GENERAL_USER);
    }

    public UserInfo getUserInfoByLoginId(String loginId) {
        return userInfoRepository.findByLoginId(loginId);
    }
}
