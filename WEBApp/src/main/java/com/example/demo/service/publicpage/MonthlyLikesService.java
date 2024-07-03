package com.example.demo.service.publicpage;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.MonthlyLikesRepository;

@Service
public class MonthlyLikesService {

    @Autowired
    private MonthlyLikesRepository monthlyLikesRepository;

    @Transactional
    public int incrementLikes(String loginId) {
        UserInfo user = monthlyLikesRepository.findById(loginId).orElse(null);
        if (user != null) {
            user.setLikes(user.getLikes() + 1);
            monthlyLikesRepository.save(user);
            return user.getLikes();
        }
        return 0;
    }

    public List<UserInfo> findMonthlyLikes() {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = currentMonth.atEndOfMonth().atTime(23, 59, 59);
        return monthlyLikesRepository.findMonthlyLikes(startOfMonth, endOfMonth);
    }
}
