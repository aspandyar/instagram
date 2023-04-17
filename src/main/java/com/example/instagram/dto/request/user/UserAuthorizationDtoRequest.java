package com.example.instagram.dto.request.user;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserAuthorizationDtoRequest {

    private String username;

    private String password;

    private LocalDateTime localDateTime;
}
