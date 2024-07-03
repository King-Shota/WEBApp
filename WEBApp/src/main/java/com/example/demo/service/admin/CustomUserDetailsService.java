package com.example.demo.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@Service("customUserDetailsService") // 名前を明示的に指定
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByLoginId(username);

        if (userInfo == null || userInfo.isDisabled()) {
            throw new UsernameNotFoundException("User not found or is disabled: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(userInfo.getLoginId())
                .password(userInfo.getPassword())
                .roles(userInfo.getAuthority().toString())
                .disabled(userInfo.isDisabled())
                .build();
    }
}
