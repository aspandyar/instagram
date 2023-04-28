package com.example.instagram.service;

import com.example.instagram.dto.request.PostLikeDislikeDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.exception.custom.RepositoryCreateException;
import com.example.instagram.exception.custom.RepositoryDeleteException;
import com.example.instagram.module.Post;
import com.example.instagram.module.PostLikeDislike;
import com.example.instagram.module.security.User;
import com.example.instagram.repository.PostLikeDislikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostLikeDislikeServiceImpl implements PostLikeDislikeService {

    private final PostLikeDislikeRepository postLikeDislikeRepository;

    private final UserService userService;

    private final PostService postService;

    @Override
    public Optional<PostLikeDislike> getById(Long id) {
        return this.postLikeDislikeRepository.findById(id);
    }

    @Override
    public PostLikeDislike getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public List<PostLikeDislike> getAll() {
        return postLikeDislikeRepository.findAll();
    }

    private PostLikeDislike save(PostLikeDislike postLikeDislike) {
        return postLikeDislikeRepository.save(postLikeDislike);
    }

    @Override
    public PostLikeDislike create(PostLikeDislikeDtoRequest dtoRequest, Principal principal, Long postId) {
        PostLikeDislike postLikeDislike = new PostLikeDislike();

        try {
            String username = principal.getName();

            User user = userService.getByUsernameThrowException(username);
            Post post = postService.getByIdThrowException(postId);

            postLikeDislike.setIsLike(dtoRequest.getIsLike());
            postLikeDislike.setUser(user);
            postLikeDislike.setPost(post);

            return this.save(postLikeDislike);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryCreateException(CustomExceptionMessage.CREATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public PostLikeDislike update(PostLikeDislikeDtoRequest dtoRequest, Long id) {
        PostLikeDislike postLikeDislike = this.getByIdThrowException(id);

        try {
            postLikeDislike.setIsLike(dtoRequest.getIsLike());

            return this.save(postLikeDislike);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryCreateException(CustomExceptionMessage.UPDATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void delete(Long id) {
        PostLikeDislike postLikeDislike = this.getByIdThrowException(id);

        try {
            postLikeDislikeRepository.delete(postLikeDislike);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryDeleteException(CustomExceptionMessage.DELETE_EXCEPTION_MESSAGE);
        }
    }
}
