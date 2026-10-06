package com.challenge.remitance_api.infrastructure.exception;

public class SameWalletException extends Throwable {
    public SameWalletException(String msg) {
        super(msg);
    }
}
