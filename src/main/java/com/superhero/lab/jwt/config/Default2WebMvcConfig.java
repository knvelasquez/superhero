package com.superhero.lab.jwt.config;

import com.superhero.lab.jwt.config.interceptor.SuperHeroHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Default2WebMvcConfig implements WebMvcConfigurer {

    private final SuperHeroHandlerInterceptor superHeroHandlerInterceptor;

    public Default2WebMvcConfig(SuperHeroHandlerInterceptor superHeroHandlerInterceptor) {
        this.superHeroHandlerInterceptor = superHeroHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(superHeroHandlerInterceptor);TBD
    }
}
