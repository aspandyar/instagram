package com.example.instagram.service;

import com.example.instagram.dto.request.PostDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.exception.custom.RepositoryCreateException;
import com.example.instagram.exception.custom.RepositoryDeleteException;
import com.example.instagram.exception.custom.RepositoryUpdateException;
import com.example.instagram.module.Post;
import com.example.instagram.module.security.User;
import com.example.instagram.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
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

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    private Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post create(PostDtoRequest dtoRequest, Principal principal) {
        Post post = new Post();

        try {
            String username = principal.getName();

            User user = userService.getByUsernameThrowException(username);

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

    @Override
    public Post update(PostDtoRequest dtoRequest, Long id) {
        Post post = this.getByIdThrowException(id);

        try {
            post.setBody(dtoRequest.getBody());

            return this.save(post);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RepositoryUpdateException(CustomExceptionMessage.UPDATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void delete(Long id) {
        Post post = this.getByIdThrowException(id);

        try {
            postRepository.delete(post);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryDeleteException(CustomExceptionMessage.DELETE_EXCEPTION_MESSAGE);
        }
    }
}
