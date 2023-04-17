package com.example.instagram.controller;

import com.example.instagram.module.Post;
import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAll() {
        List<Post> postList = postService.getAll();

        return new ResponseEntity<>(postList, HttpStatus.OK);
    }


}
