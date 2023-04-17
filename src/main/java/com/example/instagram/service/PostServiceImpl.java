package com.example.instagram.service;

import com.example.instagram.module.Post;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
