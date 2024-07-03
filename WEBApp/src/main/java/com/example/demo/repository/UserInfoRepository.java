package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Override
    @Query("SELECT u FROM UserInfo u WHERE u.deleted = false")
    List<UserInfo> findAll();

    @Query("SELECT u FROM UserInfo u WHERE u.loginId = :loginId AND u.deleted = false")
    UserInfo findByLoginId(@Param("loginId") String loginId);

    @Query("SELECT u FROM UserInfo u WHERE (:loginId IS NULL OR u.loginId LIKE %:loginId%) AND (:status IS NULL OR u.status = :status) AND (:authority IS NULL OR u.authority = :authority) AND u.deleted = false")
    List<UserInfo> searchUsers(@Param("loginId") String loginId, @Param("status") UserStatusKind status, @Param("authority") AuthorityKind authority);

    @Modifying
    @Transactional
    @Query("UPDATE UserInfo u SET u.deleted = true WHERE u.loginId = :loginId")
    void softDeleteByLoginId(@Param("loginId") String loginId);

    @Query("SELECT u FROM UserInfo u WHERE u.status = :status AND u.deleted = false")
    List<UserInfo> findAllByStatus(@Param("status") UserStatusKind status);

    @Query("SELECT u FROM UserInfo u WHERE u.loginId = :loginId AND u.status = :status AND u.deleted = false")
    List<UserInfo> findByLoginIdAndStatus(@Param("loginId") String loginId, @Param("status") UserStatusKind status);

    @Query("SELECT u FROM UserInfo u WHERE u.deleted = true")
    List<UserInfo> findByDeleted(@Param("deleted") boolean deleted);

    @Modifying
    @Transactional
    @Query("UPDATE UserInfo u SET u.deleted = false WHERE u.loginId = :loginId")
    void restoreUserByLoginId(@Param("loginId") String loginId);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserInfo u WHERE u.loginId = :loginId")
    void deleteUserPermanently(@Param("loginId") String loginId);

    // 新しいメソッドを追加
    @Query("SELECT u FROM UserInfo u WHERE u.authority = :authority AND u.deleted = false")
    List<UserInfo> findAllByAuthority(@Param("authority") AuthorityKind authority);
}
