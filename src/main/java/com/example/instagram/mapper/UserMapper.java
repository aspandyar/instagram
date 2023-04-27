package com.example.instagram.mapper;

import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.module.security.User;

public class UserMapper {

    public static UserDtoResponse userToDto(User user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();

        userDtoResponse.setUsername(user.getUsername());
        userDtoResponse.setLocalDateTime(user.getLocalDateTime());

        return userDtoResponse;
    }
}
