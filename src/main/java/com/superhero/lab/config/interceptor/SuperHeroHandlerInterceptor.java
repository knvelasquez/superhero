package com.superhero.lab.config.interceptor;

import com.filterlibrary.application.WhiteListService;
import com.superhero.lab.config.exception.SuperHeroHeaderNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SuperHeroHandlerInterceptor implements HandlerInterceptor {
    private static final String WHITE_LIST_KEY = "SuperHeroHandlerInterceptor";
    private static final String SUPER_HERO_HEADER = "SUPERHERO";
    private final WhiteListService whiteListService;

    public SuperHeroHandlerInterceptor(WhiteListService whiteListService) {
        this.whiteListService = whiteListService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String superHeroHeader = request.getHeader(SUPER_HERO_HEADER);
        String currentUrl = request.getRequestURI();

        if (!whiteListService.check(currentUrl, WHITE_LIST_KEY) || superHeroHeader != null) {
            return true;
        }
        throw new SuperHeroHeaderNotFoundException();
    }

}
