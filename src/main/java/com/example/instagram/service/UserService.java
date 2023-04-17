package com.example.instagram.service;

import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.module.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    Optional<User> getByUsername(String username);

    User getByUsernameThrowException(String username);

    User registration(UserRegistrationDtoRequest dtoRequest);

    ResponseEntity<UserDtoResponse> authorization(UserAuthorizationDtoRequest dtoRequest);
}
