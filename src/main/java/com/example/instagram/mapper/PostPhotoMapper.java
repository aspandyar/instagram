package com.example.instagram.mapper;

import com.example.instagram.dto.response.PostPhotoDtoResponse;
import com.example.instagram.module.PostPhoto;

public class PostPhotoMapper {

    public static PostPhotoDtoResponse postPhotoToDto(PostPhoto postPhoto) {

        PostPhotoDtoResponse postPhotoDtoResponse = new PostPhotoDtoResponse();

        postPhotoDtoResponse.setId(postPhoto.getId());
        postPhotoDtoResponse.setDirections(postPhoto.getDirections());
        postPhotoDtoResponse.setQueue(postPhotoDtoResponse.getQueue());

        if (postPhoto.getPost() != null) {
            postPhotoDtoResponse.setPost(PostMapper.postToDto(postPhoto.getPost()));
        }

        return postPhotoDtoResponse;
    }
}
