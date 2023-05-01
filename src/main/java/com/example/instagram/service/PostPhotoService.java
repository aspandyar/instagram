package com.example.instagram.service;

import com.example.instagram.dto.request.PostPhotoDtoRequest;
import com.example.instagram.dto.response.PostPhotoDtoResponse;
import com.example.instagram.module.PostPhoto;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PostPhotoService {

    Optional<PostPhoto> getById(Long id);

    PostPhoto getByIdThrowException(Long id);

//    PostPhoto create(PostPhotoDtoRequest dtoRequest);

    List<PostPhoto> getAll();
}
