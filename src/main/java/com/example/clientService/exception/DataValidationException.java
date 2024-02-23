package com.example.clientService.exception;

public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}