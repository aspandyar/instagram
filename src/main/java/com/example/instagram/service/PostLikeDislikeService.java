package com.example.instagram.service;

import com.example.instagram.dto.request.PostLikeDislikeDtoRequest;
import com.example.instagram.dto.response.PostLikeDislikeDtoResponse;
import com.example.instagram.module.PostLikeDislike;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PostLikeDislikeService {

    Optional<PostLikeDislike> getById(Long id);

    PostLikeDislike getByIdThrowException(Long id);

    List<PostLikeDislike> getAll();

    PostLikeDislike create(PostLikeDislikeDtoRequest dtoRequest, Principal principal, Long userId);

    PostLikeDislike update(PostLikeDislikeDtoRequest dtoRequest, Long id);

    void delete(Long id);
}
