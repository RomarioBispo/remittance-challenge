package com.challenge.remitance_api.infrastructure.exception;

public class UserIdNotFoundExceptionException extends RuntimeException{
    public UserIdNotFoundExceptionException(String msg) {
        super(msg);
    }
}
