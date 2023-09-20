package com.superhero.lab.superhero.config;

import com.filterlibrary.domain.JwtFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] ALLOWED_PATH = {
            "/api/auth/**",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/h2-console/**",
            "/jwt"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilterFactory jwtFilterFactory) throws Exception {
        return http
                .csrf()
                .disable()
                //.authorizeHttpRequests()
                //.requestMatchers(ALLOWED_PATH).permitAll()
                //.anyRequest().authenticated()
                //.and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.addFilterAfter(jwtFilterFactory.getJwtBasedAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
