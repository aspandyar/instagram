package com.example.instagram.service;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.exception.custom.RepositoryCreateException;
import com.example.instagram.module.Post;
import com.example.instagram.module.security.User;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserService userService;

    @Override
    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    private Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post create(PostDtoRequest dtoRequest) {
        Post post = new Post();

        try {
            User user = userService.getByIdThrownException(dtoRequest.getUserId());

            post.setBody(dtoRequest.getBody());
            post.setLocalDateTime(dtoRequest.getLocalDateTime());
            post.setLikeCount(dtoRequest.getLikeCount());
            post.setDislikeCount(dtoRequest.getDislikeCount());
            post.setUser(user);

            return this.save(post);
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            log.error(e.getMessage());
            throw new RepositoryCreateException(CustomExceptionMessage.CREATE_EXCEPTION_MESSAGE);
        }
    }
}
