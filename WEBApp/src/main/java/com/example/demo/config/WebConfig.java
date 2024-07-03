package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.converter.AuthorityKindConverter;
import com.example.demo.converter.UserStatusKindConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserStatusKindConverter userStatusKindConverter;
    private final AuthorityKindConverter authorityKindConverter;

    public WebConfig(UserStatusKindConverter userStatusKindConverter, AuthorityKindConverter authorityKindConverter) {
        this.userStatusKindConverter = userStatusKindConverter;
        this.authorityKindConverter = authorityKindConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(userStatusKindConverter);
        registry.addConverter(authorityKindConverter);
    }
}
