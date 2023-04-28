package com.example.instagram.service;

import com.example.instagram.dto.request.PostPhotoDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.module.PostPhoto;
import com.example.instagram.repository.PostPhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostPhotoServiceImpl implements PostPhotoService{

    private final PostPhotoRepository postPhotoRepository;

    @Override
    public Optional<PostPhoto> getById(Long id) {
        return this.postPhotoRepository.findById(id);
    }

    @Override
    public PostPhoto getByIdThrowException(Long id) {
        return getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    private PostPhoto save(PostPhoto postPhoto) {
        return postPhotoRepository.save(postPhoto);
    }

//    @Override
//    public PostPhoto create(PostPhotoDtoRequest dtoRequest) {
//
//        PostPhoto postPhoto = new PostPhoto();
//
//        try {
//            postPhoto.setDirections(dtoRequest.getDirections());
//            postPhoto.setQueue();
//
//        }
//    }
}
