package com.example.instagram.exception;

import com.example.projectfifa.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
public class ExceptionHandling {

    private final String ACCESS_DENIED_EXCEPTION = "У вас нет доступа к данному запросу.";

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponseException> AccessDeniedException(AccessDeniedException exception) {
        return createHttpResponse(FORBIDDEN, this.ACCESS_DENIED_EXCEPTION);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpResponseException> AuthenticationException(AuthenticationException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UnexpectedException.class)
    public ResponseEntity<HttpResponseException> UnexpectedException(UnexpectedException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CustomShowMessageException.class)
    public ResponseEntity<HttpResponseException> CustomShowMessageException(CustomShowMessageException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<HttpResponseException> AlreadyExistException(AlreadyExistException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponseException> NotFoundException(NotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(RepositoryCreateException.class)
    public ResponseEntity<HttpResponseException> RepositoryCreateException(RepositoryCreateException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(RepositoryUpdateException.class)
    public ResponseEntity<HttpResponseException> RepositoryUpdateException(RepositoryUpdateException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(RepositoryDeleteException.class)
    public ResponseEntity<HttpResponseException> RepositoryDeleteException(RepositoryDeleteException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponseException> MethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        if (exception.hasErrors()) {
            return createHttpResponse(BAD_REQUEST, exception.getFieldError().getDefaultMessage());
        }
        return null;
    }

    private ResponseEntity<HttpResponseException> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponseException(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
