package com.example.instagram.mapper;

import com.example.instagram.dto.response.PostCommentDtoResponse;
import com.example.instagram.module.PostComment;

public class PostCommentMapper {

    public static PostCommentDtoResponse postCommentToDto(PostComment postComment) {

        PostCommentDtoResponse postCommentDtoResponse = new PostCommentDtoResponse();

        postComment.setBody(postComment.getBody());
        postComment.setLocalDateTime(postComment.getLocalDateTime());

        if (postComment.getUser() != null) {
            postCommentDtoResponse.setUser(UserMapper.userToDto(postComment.getUser()));
        }

        if (postComment.getPost() != null) {
            postCommentDtoResponse.setPost(PostMapper.postToDto(postComment.getPost()));
        }

        return postCommentDtoResponse;
    }
}
