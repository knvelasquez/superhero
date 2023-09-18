package com.superhero.lab.jwt.config;

import com.superhero.lab.jwt.config.filter.JwtBasedAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtBasedAuthenticationFilter jwtBasedAuthenticationFilter;
    private static final String[] ALLOWED_PATH = {
            "/api/auth/**",
            "/v3/api-docs.yaml",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/h2-console/**",
            "/jwt"
    };

    public SecurityConfig(JwtBasedAuthenticationFilter jwtBasedAuthenticationFilter) {
        this.jwtBasedAuthenticationFilter = jwtBasedAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(ALLOWED_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtBasedAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
