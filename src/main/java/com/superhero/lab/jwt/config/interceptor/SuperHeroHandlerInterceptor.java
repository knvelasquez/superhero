package com.superhero.lab.jwt.config.interceptor;

import com.superhero.lab.exceptionhandler.SuperHeroHeaderNotFoundException;
import com.filterlibrary.AllowedUrls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SuperHeroHandlerInterceptor implements HandlerInterceptor {
    private static final String SUPER_HERO_HEADER = "SUPERHERO";
    private final AllowedUrls allowedUrls;

    public SuperHeroHandlerInterceptor(AllowedUrls allowedUrls) {
        this.allowedUrls = allowedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String superHeroHeader = request.getHeader(SUPER_HERO_HEADER);
        String currentUrl = request.getRequestURI();

        if (checkIfUrlPassesFilterValidation(currentUrl) || superHeroHeader != null) {
            return true;
        }
        throw new SuperHeroHeaderNotFoundException();
    }

    private Boolean checkIfUrlPassesFilterValidation(String currentUrl) {
        return allowedUrls.check(currentUrl, this.getClass());
    }
}
