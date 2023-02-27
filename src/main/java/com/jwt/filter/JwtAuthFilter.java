package com.jwt.filter;

import com.exceptionhandler.HeaderNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import com.exceptionhandler.ExceptionHandler;

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
            if(request.getRequestURI().equals("/jwt")){
                filterChain.doFilter(request, response);
                return;
            }
            final byte[] json = mapJsonFromException(new HeaderNotFoundException("Authorization header not found in request"), response);
            response.getOutputStream().write(json);
            return;
        }
        try {
            jwt = authHeader.substring(7);
            final JwtModel jwtModel = jwtApi.getAllInfo(jwt);
            if (jwtModel != null && jwtModel.getPrivileges().size() > 0) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(jwtModel.getSubject(),
                        null, jwtModel.getPrivileges().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            //Send the Serialized Response
            final byte[] json = mapJsonFromException(ex, response);
            response.getOutputStream().write(json);
            return;
        }
    }

    private byte[] mapJsonFromException(Exception ex, HttpServletResponse response) throws JsonProcessingException {
        final String result = new ObjectMapper().writeValueAsString(
                new ExceptionHandler().handleException(ex, response).getBody()
        );
        return result.getBytes();
    }
}
