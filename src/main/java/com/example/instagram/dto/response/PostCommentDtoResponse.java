package com.example.instagram.dto.response;

import com.example.instagram.module.Post;
import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostCommentDtoResponse {

    private String body;

    private UserDtoResponse user;

    private PostDtoResponse post;

    private LocalDateTime localDateTime;
}
