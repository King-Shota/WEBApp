package com.example.demo.authentication;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailsServicelmpl implements UserDetailsService {

    private final UserInfoRepository repository;

    @Value("${security.locking-border-count:3}")
    private int lockingBorderCount;

    @Value("${security.locking-time}")
    private int lockingTime;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInfo = repository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        var accountLockedTime = userInfo.getAccountLockedTime();
        var isAccountLocked = accountLockedTime != null
                && accountLockedTime.plusHours(lockingTime).isAfter(LocalDateTime.now());

        // ユーザーの権限を確認
        return User.withUsername(userInfo.getLoginId())
                .password(userInfo.getPassword())
                .authorities(userInfo.getAuthority().getCode()) // "ROLE_" プレフィックスは不要
                .accountLocked(isAccountLocked)
                .build();
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        // 認証失敗時の処理
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        // 認証成功時の処理
    }
}
