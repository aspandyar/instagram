package com.example.instagram.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class PostDtoRequest {

    private String body;

    private Long likeCount;

    private Long dislikeCount;

    private Long userId;

    private LocalDateTime localDateTime;
}
