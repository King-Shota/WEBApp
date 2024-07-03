package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.entity.UserInfo;

@Repository
public interface PublicInfoRepository extends JpaRepository<UserInfo, String> {

    List<UserInfo> findByAuthority(AuthorityKind authority);

    UserInfo findByLoginId(String loginId);
    
}
