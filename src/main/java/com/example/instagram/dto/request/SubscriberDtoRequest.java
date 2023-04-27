package com.example.instagram.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class SubscriberDtoRequest {

    private Long userId;

    private Long subscriberUserId;

    private LocalDateTime localDateTime;
}
