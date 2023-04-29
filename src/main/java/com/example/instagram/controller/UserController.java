package com.example.instagram.controller;

import com.example.instagram.dto.request.SubscriberDtoRequest;
import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.SubscriberDtoResponse;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.exception.ExceptionHandling;
import com.example.instagram.mapper.SubscriberMapper;
import com.example.instagram.mapper.UserMapper;
import com.example.instagram.module.Subscriber;
import com.example.instagram.module.security.User;
import com.example.instagram.service.SubscriberService;
import com.example.instagram.service.SubscriberServiceImpl;
import com.example.instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends ExceptionHandling {

    private final UserService userService;

    private final SubscriberService subscriberService;

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

    @PostMapping("/subscribe/{user_id}")
    public ResponseEntity<SubscriberDtoResponse> subscribe(Principal principal,
                                                        @PathVariable(name = "user_id") Long userId) {
        Subscriber subscriber = subscriberService.create(principal, userId);

        SubscriberDtoResponse dtoResponse = SubscriberMapper.subscriberToDto(subscriber);

        return new ResponseEntity<>(dtoResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/unsubscribe/{user_id}")
    public ResponseEntity<SubscriberDtoResponse> unsubscribe(@PathVariable(name = "user_id") Long userId) {
        subscriberService.delete(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
