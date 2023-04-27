package com.example.instagram.mapper;

import com.example.instagram.dto.response.PostLikeDislikeDtoResponse;
import com.example.instagram.module.PostLikeDislike;

public class PostLikeDislikeMapper {

    public static PostLikeDislikeDtoResponse postLikeDislikeToDto(PostLikeDislike postLikeDislike) {

        PostLikeDislikeDtoResponse postLikeDislikeDtoResponse = new PostLikeDislikeDtoResponse();

        postLikeDislike.setLocalDateTime(postLikeDislike.getLocalDateTime());
        postLikeDislike.setIsLike(postLikeDislike.getIsLike());

        if (postLikeDislike.getUser() != null) {
            postLikeDislikeDtoResponse.setUser(UserMapper.userToDto(postLikeDislike.getUser()));
        }

        if (postLikeDislike.getPost() != null) {
            postLikeDislikeDtoResponse.setPost(PostMapper.postToDto(postLikeDislike.getPost()));
        }

        return postLikeDislikeDtoResponse;
    }
}
