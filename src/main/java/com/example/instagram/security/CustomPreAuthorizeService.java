package com.example.instagram.security;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomPreAuthorizeService {

    public Boolean validateRequest(String authority) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(v -> v.getAuthority().equals(authority))
                && SecurityContextHolder.getContext().getAuthentication().getName().contains("@gmail.com");
    }
}
