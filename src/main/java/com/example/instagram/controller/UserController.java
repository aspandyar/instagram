package com.example.instagram.controller;

import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.exception.ExceptionHandling;
import com.example.instagram.mapper.UserMapper;
import com.example.instagram.module.security.User;
import com.example.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends ExceptionHandling {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<UserDtoResponse> registration(@Valid @RequestBody UserRegistrationDtoRequest dtoRequest) {
        User user = userService.registration(dtoRequest);
        UserDtoResponse userDtoResponse = UserMapper.userToDto(user);

        return new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserDtoResponse> authorization(@Valid @RequestBody UserAuthorizationDtoRequest dtoRequest,
                                                         HttpServletRequest request) {
        return userService.authorization(dtoRequest, request);
    }
}
