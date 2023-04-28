package com.example.instagram.mapper;

import com.example.instagram.dto.response.SubscriberDtoResponse;
import com.example.instagram.module.Subscriber;

public class SubscriberMapper {

    public static SubscriberDtoResponse subscriberToDto(Subscriber subscriber) {

        SubscriberDtoResponse subscriberDtoResponse = new SubscriberDtoResponse();

        subscriberDtoResponse.setId(subscriber.getId());
        subscriberDtoResponse.setLocalDateTime(subscriber.getLocalDateTime());

        if (subscriber.getUser() != null) {
            subscriberDtoResponse.setUser(UserMapper.userToDto(subscriber.getUser()));
        }

        if (subscriber.getSubscriberUser() != null) {
            subscriberDtoResponse.setSubscriberUser(UserMapper.userToDto(subscriber.getSubscriberUser()));
        }

        return subscriberDtoResponse;
    }
}
