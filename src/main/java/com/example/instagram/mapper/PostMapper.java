package com.example.instagram.mapper;

import com.example.instagram.dto.response.PostDtoResponse;
import com.example.instagram.module.Post;

public class PostMapper {

    public static PostDtoResponse postToDto(Post post) {
        PostDtoResponse postDtoResponse = new PostDtoResponse();

        postDtoResponse.setId(post.getId());

        postDtoResponse.setBody(post.getBody());
//
//        if(post.getUser() != null) {
//            postDtoResponse.setUser(UserMapper.userToDto(post.getUser()));
//        }

        postDtoResponse.setLocalDateTime(post.getLocalDateTime());

        return postDtoResponse;
    }
}
