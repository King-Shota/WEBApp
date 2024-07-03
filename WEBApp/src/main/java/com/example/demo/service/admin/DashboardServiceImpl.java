package com.example.demo.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.model.LikeRanking;
import com.example.demo.repository.LikeRankingRepository;
import com.example.demo.repository.UserInfoRepository; // UserInfoRepository の import が必要です

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final LikeRankingRepository likeRankingRepository;
    private final UserInfoRepository userInfoRepository; // 追加

    @Override
    public List<LikeRanking> getYearlyLikes() {
        return likeRankingRepository.findYearlyLikes();
    }

    @Override
    public List<LikeRanking> getMonthlyLikes() {
        return likeRankingRepository.findMonthlyLikes();
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll(); // 実際の実装に応じて変更してください
    }
}