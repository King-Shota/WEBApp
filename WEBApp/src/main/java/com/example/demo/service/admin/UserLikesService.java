package com.example.demo.service.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserLikesRepository;

@Service
public class UserLikesService {

    @Autowired
    private UserLikesRepository userLikesRepository;

    public List<Object[]> getYearlyLikesRanking() {
        LocalDateTime startOfYear = LocalDateTime.of(LocalDate.now().withDayOfYear(1), LocalTime.MIN);
        LocalDateTime endOfYear = LocalDateTime.of(LocalDate.now().withDayOfYear(LocalDate.now().lengthOfYear()), LocalTime.MAX);
        return userLikesRepository.findLikesRankingBetween(startOfYear, endOfYear);
    }

    public List<Object[]> getMonthlyLikesRanking() {
        LocalDateTime startOfMonth = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
        LocalDateTime endOfMonth = LocalDateTime.of(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()), LocalTime.MAX);
        return userLikesRepository.findLikesRankingBetween(startOfMonth, endOfMonth);
    }
}
