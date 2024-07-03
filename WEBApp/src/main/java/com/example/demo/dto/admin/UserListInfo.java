package com.example.demo.dto.admin;

import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;

import lombok.Data;

@Data
public class UserListInfo {
    
    /** ログインID */
    private String loginId;
    
    /** メールアドレス */
    private String email;
    
    /** お名前 */
    private String name;
    
    /** ログイン失敗回数 */
    private int loginFailureCount;
    
    /** アカウントロック日時 */
    private LocalDateTime accountLockedTime;
    
    /** アカウント状態 */
    private UserStatusKind status;
    
    /** 権限 */
    private AuthorityKind authority;
    
    /** 登録日時　*/
    private LocalDateTime createTime;
    
    /** 更新日時 */
    private LocalDateTime updateTime;

}