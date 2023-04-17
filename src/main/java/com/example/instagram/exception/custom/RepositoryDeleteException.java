package com.example.instagram.exception.custom;

public class RepositoryDeleteException extends RuntimeException {
    public RepositoryDeleteException(String message) {
        super(message);
    }
}
