package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserInfo;

@Repository
public interface MonthlyLikesRepository extends JpaRepository<UserInfo, String> {

	 @Query("SELECT u FROM UserInfo u WHERE u.createTime BETWEEN :startOfMonth AND :endOfMonth AND u.authority = 'ROLE_USER' ORDER BY u.likes DESC")
	    List<UserInfo> findMonthlyLikes(@Param("startOfMonth") LocalDateTime startOfMonth, @Param("endOfMonth") LocalDateTime endOfMonth);
    // 追加: loginIdによる検索
    UserInfo findByLoginId(String loginId);
}
