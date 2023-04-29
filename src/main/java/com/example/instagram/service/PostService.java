package com.example.instagram.service;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.module.Post;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<Post> getById(Long id);

    Post getByIdThrowException(Long id);

    List<Post> getAll();

    Post create(PostDtoRequest dtoRequest, Principal principal);

    Post update(PostDtoRequest dtoRequest, Long id);

    void delete(Long id);

    void createLikeDislike(Long id, Boolean isLike);

    void updateLikeDislike(Long postId, Long postLikeId, Boolean isLike);

    void deleteLikeDislike(Long id, Boolean isLike);
}
