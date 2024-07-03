package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
public class UserInfo {

    @Id
    @Column(name = "login_id")
    private String loginId;

    private String password;
    private String email;
    private String name;

    @Column(name = "login_failure_count")
    private int loginFailureCount = 0;

    @Column(name = "account_locked_time")
    private LocalDateTime accountLockedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatusKind status;

    @Convert(converter = UserAuthorityConverter.class)
    private AuthorityKind authority;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    private boolean deleted = false;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private String profileImage;
    private String furigana;
    private String gender;
    private Integer age;
    private String selfIntroduction;
    
    // likesカラムを追加
    @Column(name = "likes")
    private int likes;

    public UserInfo() {
    }

    // 各種メソッド

    public UserInfo incrementLoginFailureCount() {
        return new UserInfo(loginId, password, email, name, ++loginFailureCount, accountLockedTime, status, authority, createTime, deleted, updateTime, profileImage, furigana, gender, age, selfIntroduction, likes);
    }

    public UserInfo resetLoginFailureInfo() {
        return new UserInfo(loginId, password, email, name, 0, null, status, authority, createTime, deleted, updateTime, profileImage, furigana, gender, age, selfIntroduction, likes);
    }

    public UserInfo updateAccountLocked() {
        return new UserInfo(loginId, password, email, name, 0, LocalDateTime.now(), status, authority, createTime, deleted, updateTime, profileImage, furigana, gender, age, selfIntroduction, likes);
    }

    public boolean isDisabled() {
        return status == UserStatusKind.DISABLED;
    }
    
    // プロフィール画像のゲッターとセッター
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    // ふりがなのゲッターとセッター
    public String getFurigana() {
        return furigana;
    }

    public void setFurigana(String furigana) {
        this.furigana = furigana;
    }

    // 性別のゲッターとセッター
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // 年齢のゲッターとセッター
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // 自己紹介のゲッターとセッター
    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    // いいねのゲッターとセッター
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
