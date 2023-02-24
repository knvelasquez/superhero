package com.jwt.filter;

import com.jwt.model.JwtModel;
import com.jwt.service.Hs256JwtApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private Hs256JwtApi jwtApi;
    private final String AUTHORIZATION = "Authorization";
    private final String BEARER = "Bearer ";

    @Autowired
    public JwtAuthFilter(Hs256JwtApi jwtApi) {
        this.jwtApi = jwtApi;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;

        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        final JwtModel jwtModel = jwtApi.getAllInfo(jwt);

        if (jwtModel != null && jwtModel.getPrivileges().size() > 0) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtModel.getSubject(),
                    null, jwtModel.getPrivileges().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
