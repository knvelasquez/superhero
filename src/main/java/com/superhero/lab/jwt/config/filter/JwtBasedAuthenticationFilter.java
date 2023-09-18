package com.superhero.lab.jwt.config.filter;

import com.jwtlibrary.adapter.factory.JwtFactory;
import com.jwtlibrary.domain.JwtDecoder;
import com.jwtlibrary.model.SecurityKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtBasedAuthenticationFilter extends OncePerRequestFilter {
    private static final JwtDecoder jwtDecoder = JwtFactory.getDecoder();
    private static final String AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String jwtValue = request.getHeader(AUTHORIZATION);
        SecurityKey jwtDecoded = jwtDecoder.decode(jwtValue);
        Optional<SecurityKey> jwtResult = Optional.ofNullable(jwtDecoded);
        jwtResult.ifPresent(jwt -> {
            List<String> extraClaims = jwt.getExtraClaims();
            if (!extraClaims.isEmpty()) {
                String subject = jwt.getSubject();
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(toUserPassAuthToken(
                                subject, extraClaims
                        ));
            }
        });
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken toUserPassAuthToken(String subject, List<String> extraClaims) {
        return new UsernamePasswordAuthenticationToken(
                subject,
                null,
                toGrantedAuth(extraClaims)
        );
    }

    private Collection<GrantedAuthority> toGrantedAuth(List<String> extraClaims) {
        return extraClaims.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}