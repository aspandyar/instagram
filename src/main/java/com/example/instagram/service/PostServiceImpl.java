package com.example.instagram.service;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.module.Post;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private Post save(Post post) {return postRepository.save(post);}

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Post create(PostDtoRequest dtoRequest) {
        return null;
    }

    @Override
    public Post update(PostDtoRequest dtoRequest, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
