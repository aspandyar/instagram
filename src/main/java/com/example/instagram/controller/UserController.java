package com.example.instagram.controller;

import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.exception.ExceptionHandling;
import com.example.instagram.module.User;
import com.example.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends ExceptionHandling {

    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@Valid @RequestBody UserRegistrationDtoRequest dtoRequest) {
        userService.registration(dtoRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authorization")
    public ResponseEntity<UserDtoResponse> authorization(@Valid @RequestBody UserAuthorizationDtoRequest dtoRequest,
                                                         HttpServletRequest request) {
        return userService.authorization(dtoRequest, request);
    }
}
