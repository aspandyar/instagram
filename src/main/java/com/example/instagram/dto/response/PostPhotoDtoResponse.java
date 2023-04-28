package com.example.instagram.dto.response;

import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPhotoDtoResponse {

    private Long id;

    private String directions;

    private Integer queue;

    private PostDtoResponse post;

}
