package com.example.security_test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.formLogin().loginPage("/loginForm")
                .loginProcessingUrl("/loginProc") //로그인 진행주소
                .failureUrl("/")
                .defaultSuccessUrl("/");

        http.authorizeRequests()
                .mvcMatchers("/", "/css/**", "/loginForm", "/signUpForm", "/layouts/**"
                        , "/check-email-token").permitAll()
                .mvcMatchers("/").hasRole("USER")
                .anyRequest().permitAll();

        return http.build();
    }
}
