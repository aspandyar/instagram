package com.example.instagram.service;

import com.example.instagram.dto.request.user.UserAuthorizationDtoRequest;
import com.example.instagram.dto.request.user.UserRegistrationDtoRequest;
import com.example.instagram.dto.response.UserDtoResponse;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.*;
import com.example.instagram.mapper.UserMapper;
import com.example.instagram.module.security.User;
import com.example.instagram.repository.UserRepository;
import com.example.instagram.security.JWTTokenProvider;
import com.example.instagram.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Lazy
    private final AuthenticationManager authenticationManager;

    private final JWTTokenProvider jwtTokenProvider;

    private final String USERNAME_ALREADY_EXIST_EXCEPTION = "Данный логин уже занят.";
    private final String AUTHENTICATION_EXCEPTION = "Логин или пароль неправольно введены.";
    private final String AUTHENTICATION_IS_ACTIVE_EXCEPTION = "Ваш аккаунт не активен.";
    private final String AUTHENTICATION_IS_NON_LOCKED_EXCEPTION = "Ваш аккаунт заблокирован.";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getByUsernameThrowException(username);

        return new UserPrincipal(user);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getByUsernameThrowException(String username) {
        return this.getByUsername(username).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User registration(UserRegistrationDtoRequest dtoRequest) {
        String username = dtoRequest.getUsername().toLowerCase().trim();
        String password = dtoRequest.getPassword().trim();

        Optional<User> user = this.getByUsername(username);

        if (user.isPresent()) throw new AlreadyExistException(this.USERNAME_ALREADY_EXIST_EXCEPTION);

        try {
            User createdUser = new User();

            createdUser.setUsername(username);
            createdUser.setPassword(encoder.encode(password));

            return this.save(createdUser);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryCreateException(CustomExceptionMessage.CREATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public ResponseEntity<UserDtoResponse> authorization(UserAuthorizationDtoRequest dtoRequest, HttpServletRequest request) {
        String username = dtoRequest.getUsername().toLowerCase().trim();
        String password = dtoRequest.getPassword().trim();

        try {
            this.authentication(username, password);
        } catch (Exception e) {
            if (e.getMessage().equals("User is disabled")) {
                throw new AuthenticationException(this.AUTHENTICATION_IS_ACTIVE_EXCEPTION);
            }

            else if (e.getMessage().equals("User account is locked")) {
                throw new AuthenticationException(this.AUTHENTICATION_IS_NON_LOCKED_EXCEPTION);
            }
//            System.out.println(e.getMessage());
            throw new AuthenticationException(this.AUTHENTICATION_EXCEPTION);
        }

        try {
            User user = this.getByUsernameThrowException(username);
            UserPrincipal userPrincipal = new UserPrincipal(user);

            String IP = jwtTokenProvider.getIP(request);

            HttpHeaders httpHeaders = this.getJWTHeader(userPrincipal, IP);

            return new ResponseEntity<>(UserMapper.userToDto(user), httpHeaders, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnexpectedException(CustomExceptionMessage.UNEXPECTED_EXCEPTION_MESSAGE);
        }
    }

    private HttpHeaders getJWTHeader(UserPrincipal userPrincipal, String IP) {
        HttpHeaders httpHeaders = new HttpHeaders();

        String JWT = jwtTokenProvider.generateToken(userPrincipal, IP);

        httpHeaders.add(HttpHeaders.AUTHORIZATION, JWT);

        return httpHeaders;
    }

    private void authentication(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getByIdThrownException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
