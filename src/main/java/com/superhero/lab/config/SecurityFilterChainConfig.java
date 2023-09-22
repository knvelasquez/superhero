package com.superhero.lab.config;

import com.filterlibrary.application.WhiteListService;
import com.filterlibrary.domain.InterceptorFactory;
import com.superhero.lab.config.interceptor.SuperHeroHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityFilterChainConfig {
    private static final String AUTHORIZATION_BEARER_TOKEN_INTERCEPTOR = "AuthorizationBearerToken";
    private static final String JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR = "JwtBasedAuthenticationHandlerInterceptor";
    private static final String SUPER_HERO_HANDLER_INTERCEPTOR = "SuperHeroHandlerInterceptor";

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspect) {
        return new MvcRequestMatcher.Builder(introspect);
    }

    @Bean
    public SecurityFilterChain securityFilterChainConfigBean(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                mvc.pattern("/health"),
                                mvc.pattern("/superhero/**"),
                                mvc.pattern("/h2-console/**"),
                                mvc.pattern("/api/auth/**"),
                                mvc.pattern("/v3/api-docs.yaml"),
                                mvc.pattern("/v3/api-docs/**"),
                                mvc.pattern("/swagger-ui/**"),
                                mvc.pattern("/swagger-ui.html")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public WebMvcConfigurer webMvcConfig(InterceptorFactory interceptorFactory,
                                         SuperHeroHandlerInterceptor superHeroHandlerInterceptor) throws Exception {
        return interceptorFactory.from(new ArrayList<>(
                Arrays.asList(AUTHORIZATION_BEARER_TOKEN_INTERCEPTOR, superHeroHandlerInterceptor, JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR))
        );
    }

    @Bean
    public Boolean whiteListConfig(WhiteListService whiteListService) {

        whiteListService.update("/health", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/health", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/health", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/index.html", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/index.html", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/index.html", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/swagger-initializer.js", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/swagger-initializer.js", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/swagger-initializer.js", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/swagger-ui.css", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/swagger-ui.css", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/swagger-ui.css", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/swagger-ui-standalone-preset.js", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/swagger-ui-standalone-preset.js", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/swagger-ui-standalone-preset.js", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/swagger-ui-bundle.js", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/swagger-ui-bundle.js", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/swagger-ui-bundle.js", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/index.css", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/index.css", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/index.css", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/favicon-32x32.png", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/favicon-32x32.png", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/favicon-32x32.png", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/swagger-ui/favicon-16x16.png", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/swagger-ui/favicon-16x16.png", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/swagger-ui/favicon-16x16.png", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/v3/api-docs/swagger-config", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/v3/api-docs/swagger-config", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/v3/api-docs/swagger-config", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        whiteListService.update("/v3/api-docs", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/v3/api-docs", SUPER_HERO_HANDLER_INTERCEPTOR, false);
        whiteListService.update("/v3/api-docs", JWT_BASED_AUTHENTICATION_HANDLER_INTERCEPTOR, false);

        return true;
    }

}
