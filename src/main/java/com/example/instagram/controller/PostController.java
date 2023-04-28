package com.example.instagram.controller;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.dto.response.PostDtoResponse;
import com.example.instagram.mapper.PostMapper;
import com.example.instagram.module.Post;
import com.example.instagram.repository.PostRepository;
import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDtoResponse> create(@Valid @RequestBody PostDtoRequest dtoRequest) {
        Post post = postService.create(dtoRequest);

        PostDtoResponse dtoResponse = PostMapper.postToDto(post);

        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
    }
}
