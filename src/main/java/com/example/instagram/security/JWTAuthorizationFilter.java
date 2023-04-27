package com.example.instagram.security;

import com.example.instagram.constant.WebConstant;
import com.example.instagram.module.security.User;
import com.example.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final JWTTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (Arrays.stream(WebConstant.PERMIT_ALL_URL).anyMatch(v -> v.equals(request.getRequestURI()))) {
            filterChain.doFilter(request, response);
            return;
        }

        String headerAuthorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (headerAuthorization == null || !headerAuthorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String JWT = headerAuthorization.substring("Bearer ".length());

            String username = jwtTokenProvider.getSubject(JWT);

            jwtTokenProvider.isTokenValid(JWT, request);

            User user = userService.getByUsernameThrowException(username);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            if (!user.getIsNonLocked()) throw new AuthenticationException("");

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal.getUsername(), null, userPrincipal.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        filterChain.doFilter(request, response);
    }
}
