package com.example.demo.service.admin;

import java.util.List;

import com.example.demo.entity.UserInfo;
import com.example.demo.model.LikeRanking;

public interface DashboardService {
    List<LikeRanking> getYearlyLikes();
    List<LikeRanking> getMonthlyLikes();
    // 追加
    List<UserInfo> getAllUsers(); // ここに UserInfo の import が必要です
}