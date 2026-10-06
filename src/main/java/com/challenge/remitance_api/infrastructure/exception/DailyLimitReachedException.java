package com.challenge.remitance_api.infrastructure.exception;

public class DailyLimitReachedException extends RuntimeException{
    public DailyLimitReachedException(String msg) {
        super(msg);
    }
}
