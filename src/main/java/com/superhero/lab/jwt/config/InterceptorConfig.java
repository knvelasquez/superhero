package com.superhero.lab.jwt.config;

import com.filterlibrary.application.WhiteListService;
import com.filterlibrary.domain.InterceptorFactory;
import com.superhero.lab.jwt.config.interceptor.SuperHeroHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class InterceptorConfig {
    private static final String AUTHORIZATION_BEARER_TOKEN_INTERCEPTOR = "AuthorizationBearerToken";

    @Bean
    public WebMvcConfigurer webMvcConfig(InterceptorFactory interceptorFactory,
                                         SuperHeroHandlerInterceptor superHeroHandlerInterceptor) throws Exception {
        return interceptorFactory.from(new ArrayList<>(
                Arrays.asList(AUTHORIZATION_BEARER_TOKEN_INTERCEPTOR, superHeroHandlerInterceptor))
        );
    }

    @Bean
    public Boolean whiteListConfig(WhiteListService whiteListService) {
        whiteListService.update("/jwt", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/jwt", "SuperHeroHandlerInterceptor", false);
        whiteListService.update("/superhero", "AuthorizationBearerTokenHandlerInterceptor", false);
        whiteListService.update("/superhero", "SuperHeroHandlerInterceptor", false);
        return true;
    }

}
