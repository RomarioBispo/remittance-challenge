package com.challenge.remitance_api.infrastructure.exception;

public class PersonIdNotFoundExceptionException extends RuntimeException{
    public PersonIdNotFoundExceptionException(String msg) {
        super(msg);
    }
}
