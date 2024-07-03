package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private final String USERNAME_PARAMETER = "loginId";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/signup", "/inquiry", "/css/**", "/js/**", "/publicPage", "/like", "/monthly-likes", "/user-details/{loginId}").permitAll() // ここに /like を追加
                .requestMatchers("/admindashboard", "/user/settings/**").hasAuthority("ROLE_ADMIN") //管理者
                .anyRequest().authenticated())
            .formLogin(login -> login
                .loginPage("/login")
                .usernameParameter(USERNAME_PARAMETER)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler())
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll())
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/like")); // ここに /like を追加してCSRFを無視

        return http.build();
    }

    @Bean
    AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
    }
}
