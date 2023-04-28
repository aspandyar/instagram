package com.example.instagram.service;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.module.Post;

import java.security.Principal;
import java.util.Optional;

public interface PostService {

    Optional<Post> getById(Long id);

    Post getByIdThrowException(Long id);

    Post create(PostDtoRequest dtoRequest);
}
