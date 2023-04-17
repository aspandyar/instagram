package com.example.instagram.service;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.module.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getAll();

    Optional<Post> getById(Long id);

    Post getByIdThrowException(Long id);

    Post create(PostDtoRequest dtoRequest);

    Post update(PostDtoRequest dtoRequest, Long id);

    void delete(Long id);
}
