package com.challenge.remitance_api.infrastructure.exception;

public class SameWalletException extends RuntimeException {
    public SameWalletException(String msg) {
        super(msg);
    }
}
