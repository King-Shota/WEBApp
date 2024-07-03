package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.LikeRanking;

@Repository
public class LikeRankingRepository {

    public List<LikeRanking> findYearlyLikes() {
        // 年間ランキングデータを取得するロジックを実装
        return List.of(
            new LikeRanking(1, "アイテム1", 120),
            new LikeRanking(2, "アイテム2", 110)
            // 追加のデータ
        );
    }

    public List<LikeRanking> findMonthlyLikes() {
        // 月間ランキングデータを取得するロジックを実装
        return List.of(
            new LikeRanking(1, "アイテム3", 30),
            new LikeRanking(2, "アイテム4", 25)
            // 追加のデータ
        );
    }
}