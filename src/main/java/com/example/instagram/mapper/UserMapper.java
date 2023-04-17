package com.example.instagram.mapper;

import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.module.User;

public class UserMapper {

    public static UserDtoResponse userToDto(User user) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();

        userDtoResponse.setId(user.getId());

        userDtoResponse.setUsername(user.getUsername());

        userDtoResponse.setPassword(user.getPassword());

        userDtoResponse.setLocalDateTime(user.getLocalDateTime());

        return userDtoResponse;
    }
}
