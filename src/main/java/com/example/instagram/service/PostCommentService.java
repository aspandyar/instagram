package com.example.instagram.service;

import com.example.instagram.dto.request.PostCommentDtoRequest;
import com.example.instagram.dto.response.PostCommentDtoResponse;
import com.example.instagram.module.PostComment;

import java.security.Principal;
import java.util.Optional;

public interface PostCommentService {

    Optional<PostComment> getById(Long id);

    PostComment getByIdThrowException(Long id);

    PostComment create(PostCommentDtoRequest dtoRequest, Principal principal);

    PostComment update(PostCommentDtoRequest dtoRequest, Long id);

    void delete(Long id);
}
