package com.example.instagram.dto.response;

import com.example.instagram.module.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private String body;

    private LocalDateTime localDateTime;
}
