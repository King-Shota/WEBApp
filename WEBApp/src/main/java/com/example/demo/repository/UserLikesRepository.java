package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserInfo;
import com.example.demo.entity.UserLikes;

public interface UserLikesRepository extends JpaRepository<UserLikes, Long> {

    @Query("SELECT COUNT(ul) FROM UserLikes ul WHERE ul.user = :user AND ul.likedAt BETWEEN :start AND :end")
    int countLikesBetween(@Param("user") UserInfo user, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT u.user.name, COUNT(u) as count FROM UserLikes u WHERE u.likedAt BETWEEN :start AND :end GROUP BY u.user.name ORDER BY count DESC")
    List<Object[]> findLikesRankingBetween(LocalDateTime start, LocalDateTime end);

    
    List<UserLikes> findByUser(UserInfo user);
}
