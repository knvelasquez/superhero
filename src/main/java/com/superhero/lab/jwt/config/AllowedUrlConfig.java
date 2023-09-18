package com.superhero.lab.jwt.config;

import com.filterlibrary.AllowedUrls;
import com.filterlibrary.interceptor.AuthorizationBearerTokenHandlerInterceptor;
import com.superhero.lab.jwt.config.interceptor.SuperHeroHandlerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllowedUrlConfig {

    private final AllowedUrls allowed;

    public AllowedUrlConfig(AllowedUrls allowed) {
        this.allowed = allowed;
        this.allowed.off("/jwt", AuthorizationBearerTokenHandlerInterceptor.class);
        this.allowed.off("/jwt", SuperHeroHandlerInterceptor.class);
        this.allowed.off("/superhero", SuperHeroHandlerInterceptor.class);
    }

}
