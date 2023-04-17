package com.example.instagram.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserDtoRequest {

    private String username;

    private String password;

    private LocalDateTime localDateTime;
}
