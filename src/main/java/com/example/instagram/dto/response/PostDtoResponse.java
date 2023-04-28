package com.example.instagram.dto.response;

import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDtoResponse {

    private Long id;

    private String body;

    private Long likeCount;

    private Long dislikeCount;

    private UserDtoResponse user;

    private LocalDateTime localDateTime;
}
