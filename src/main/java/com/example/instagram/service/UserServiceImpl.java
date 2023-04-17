package com.example.instagram.service;

import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.AlreadyExistException;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.exception.custom.RepositoryCreateException;
import com.example.instagram.module.User;
import com.example.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final String USERNAME_ALREADY_EXIST_EXCEPTION = "Данный логин уже занят.";
    private final String AUTHENTICATION_EXCEPTION = "Логин или пароль неправольно введены.";
    private final String AUTHENTICATION_IS_ACTIVE_EXCEPTION = "Ваш аккаунт не активен.";
    private final String AUTHENTICATION_IS_NON_LOCKED_EXCEPTION = "Ваш аккаунт заблокирован.";

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUsernameThrowException(String username) {
        return this.getByUsername(username).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    private User save(User user) {return userRepository.save(user);}

    @Override
    public User registration(UserRegistrationDtoRequest dtoRequest) {
        String username = dtoRequest.getUsername().toLowerCase().trim();
        String password = dtoRequest.getPassword().trim();

        Optional<User> user = this.getByUsername(username);

        if(user.isPresent()) throw new AlreadyExistException(this.USERNAME_ALREADY_EXIST_EXCEPTION);

        try {
            User createdUser = new User();

            createdUser.setUsername(username);

            createdUser.setPassword(encoder.encode(password));

            LocalDateTime localDateTime = LocalDateTime.now();

            createdUser.setLocalDateTime(localDateTime);


            return this.save(createdUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryCreateException(CustomExceptionMessage.CREATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public ResponseEntity<UserDtoResponse> authorization(UserAuthorizationDtoRequest dtoRequest) {
        String username = dtoRequest.getUsername().toLowerCase().trim();
        String password = dtoRequest.getPassword().trim();

        this.authenticate(username, password);


    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
