package com.example.instagram.controller;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.dto.request.PostLikeDislikeDtoRequest;
import com.example.instagram.dto.response.PostDtoResponse;
import com.example.instagram.dto.response.PostLikeDislikeDtoResponse;
import com.example.instagram.mapper.PostLikeDislikeMapper;
import com.example.instagram.mapper.PostMapper;
import com.example.instagram.module.Post;
import com.example.instagram.module.PostLikeDislike;
import com.example.instagram.repository.PostRepository;
import com.example.instagram.service.PostLikeDislikeService;
import com.example.instagram.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostLikeDislikeService postLikeDislikeService;

//    @GetMapping("/")
//    public ResponseEntity<PostDtoResponse> getAll() {
//        List<Post> postList = postService.getAll();
//
//        PostDtoResponse dtoResponse = PostMapper.postToDto(postList);
//
//        return new ResponseEntity<>(postList, HttpStatus.OK);
//    }

    @PostMapping("/create")
    public ResponseEntity<PostDtoResponse> create(@Valid @RequestBody PostDtoRequest dtoRequest, Principal principal) {
        Post post = postService.create(dtoRequest, principal);

        PostDtoResponse dtoResponse = PostMapper.postToDto(post);

        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDtoResponse> update(@Valid @RequestBody PostDtoRequest dtoRequest,
                                                  @PathVariable(name = "id") Long id) {
        Post post = postService.update(dtoRequest, id);

        PostDtoResponse postDtoResponse = PostMapper.postToDto(post);

        return new ResponseEntity<>(postDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PostDtoResponse> delete(@PathVariable(name = "id") Long id) {
        postService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create/like/{post_id}")
    public ResponseEntity<PostLikeDislikeDtoResponse> createLike(@Valid @RequestBody PostLikeDislikeDtoRequest dtoRequest, Principal principal,
                                                                 @PathVariable(name = "post_id") Long id) {

        PostLikeDislike postLikeDislike = postLikeDislikeService.create(dtoRequest, principal, id);

        PostLikeDislikeDtoResponse dtoResponse = PostLikeDislikeMapper.postLikeDislikeToDto(postLikeDislike);

        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/like/{post_id}")
    public ResponseEntity<PostLikeDislikeDtoResponse> updateLike(@Valid @RequestBody PostLikeDislikeDtoRequest dtoRequest,
                                                                 @PathVariable(name = "post_id") Long id) {
        PostLikeDislike postLikeDislike = postLikeDislikeService.update(dtoRequest, id);

        PostLikeDislikeDtoResponse dtoResponse = PostLikeDislikeMapper.postLikeDislikeToDto(postLikeDislike);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/like/{post_id}")
    public ResponseEntity<PostLikeDislike> deleteLike(@PathVariable(name = "post_id") Long id) {
        postLikeDislikeService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
