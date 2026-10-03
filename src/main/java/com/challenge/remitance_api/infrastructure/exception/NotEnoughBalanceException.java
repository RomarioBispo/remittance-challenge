package com.challenge.remitance_api.infrastructure.exception;

public class NotEnoughBalanceException extends RuntimeException{
    public NotEnoughBalanceException(String msg) {
        super(msg);
    }
}
