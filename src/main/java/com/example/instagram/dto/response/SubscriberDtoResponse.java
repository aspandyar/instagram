package com.example.instagram.dto.response;

import com.example.instagram.module.security.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SubscriberDtoResponse {

    private Long id;

    private UserDtoResponse user;

    private UserDtoResponse subscriberUser;

    private LocalDateTime localDateTime;
}
