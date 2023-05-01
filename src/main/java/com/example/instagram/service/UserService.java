package com.example.instagram.service;

import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.module.security.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getById(Long id);

    User getByIdThrownException(Long id);

    List<User> getAll();

    Optional<User> getByUsername(String username);

    User getByUsernameThrowException(String username);

    User registration(UserRegistrationDtoRequest dtoRequest);

    ResponseEntity<UserDtoResponse> authorization(UserAuthorizationDtoRequest dtoRequest, HttpServletRequest request);

}
