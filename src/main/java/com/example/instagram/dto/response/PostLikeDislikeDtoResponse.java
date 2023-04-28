package com.example.instagram.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostLikeDislikeDtoResponse {

    private Long id;

    private Boolean isLike;

    private PostDtoResponse post;

    private UserDtoResponse user;

    private LocalDateTime localDateTime;
}
