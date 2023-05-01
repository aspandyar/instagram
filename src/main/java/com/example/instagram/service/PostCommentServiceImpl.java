package com.example.instagram.service;

import com.example.instagram.dto.request.PostCommentDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.exception.custom.RepositoryDeleteException;
import com.example.instagram.exception.custom.RepositoryUpdateException;
import com.example.instagram.module.Post;
import com.example.instagram.module.PostComment;
import com.example.instagram.module.security.User;
import com.example.instagram.repository.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostCommentServiceImpl implements PostCommentService {

    private final PostCommentRepository postCommentRepository;

    private final UserService userService;

    private final PostService postService;

    @Override
    public Optional<PostComment> getById(Long id) {
        return postCommentRepository.findById(id);
    }

    @Override
    public PostComment getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    private PostComment save(PostComment postComment) {
        return postCommentRepository.save(postComment);
    }

    @Override
    public List<PostComment> getAll() {
        return postCommentRepository.findAll();
    }

    @Override
    public PostComment create(PostCommentDtoRequest dtoRequest, Principal principal) {
        PostComment postComment = new PostComment();

        try {
            String username = principal.getName();

            User user = userService.getByUsernameThrowException(username);
            Post post = postService.getByIdThrowException(dtoRequest.getPostId());

            if (this.getByIdThrowException(dtoRequest.getPostCommentId()) != null) {
                PostComment postComment1 = this.getByIdThrowException(dtoRequest.getPostCommentId());
                postComment.setPostComment(postComment1);
            }
            postComment.setUser(user);
            postComment.setPost(post);
            postComment.setBody(dtoRequest.getBody());


            return this.save(postComment);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryUpdateException(CustomExceptionMessage.CREATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public PostComment update(PostCommentDtoRequest dtoRequest, Long id) {
        PostComment postComment = this.getByIdThrowException(id);

        try {
            postComment.setBody(dtoRequest.getBody());

            return this.save(postComment);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RepositoryUpdateException(CustomExceptionMessage.UPDATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void delete(Long id) {
        PostComment postComment = this.getByIdThrowException(id);

        try {
            postCommentRepository.delete(postComment);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryDeleteException(CustomExceptionMessage.DELETE_EXCEPTION_MESSAGE);
        }
    }
}
