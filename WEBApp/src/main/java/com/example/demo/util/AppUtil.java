package com.example.demo.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.demo.constant.AuthorityKind;

@Component
public class AppUtil {

    public static String getCurrentUserAuthority() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String role = ((UserDetails) principal).getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority())
                .orElse("");
            AuthorityKind authorityKind = AuthorityKind.from(role);
            return authorityKind.getDisplayValue();
        }
        return AuthorityKind.UNKNOWN.getDisplayValue();
    }

    public static String getMessage(MessageSource messageSource, String key, Object... params) {
        return messageSource.getMessage(key, params, Locale.JAPAN);
    }
}
