package com.tp.CRUD.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
