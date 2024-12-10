package com.example.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() 
            .authorizeRequests()
            .requestMatchers("/","/register", "/login", "/css/*", "/assets/*", "/js/*") 
            .permitAll()
            .anyRequest()
            .authenticated() 
            .and()
            .formLogin() 
            .and()
            .logout()
            .permitAll(); 

        return http.build();
    }
}
