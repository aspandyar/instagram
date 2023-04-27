package com.example.instagram.mapper;

import com.example.instagram.dto.response.PostDtoResponse;
import com.example.instagram.module.Post;

public class PostMapper {

    public static PostDtoResponse postToDto(Post post) {

        PostDtoResponse postDtoResponse = new PostDtoResponse();

        postDtoResponse.setBody(post.getBody());
        postDtoResponse.setLikeCount(post.getLikeCount());
        postDtoResponse.setDislikeCount(post.getDislikeCount());
        postDtoResponse.setLocalDateTime(post.getLocalDateTime());

        if (post.getUser() != null) {
            postDtoResponse.setUser(UserMapper.userToDto(post.getUser()));
        }

        return postDtoResponse;
    }
}
