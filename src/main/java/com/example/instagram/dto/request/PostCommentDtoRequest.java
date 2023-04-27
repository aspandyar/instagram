package com.example.instagram.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class PostCommentDtoRequest {

    private String body;

    private Long userId;

    private Long postId;

    private LocalDateTime localDateTime;
}
