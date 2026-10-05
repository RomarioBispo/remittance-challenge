package com.challenge.remitance_api.infrastructure.controller.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            String msg,
                            HttpStatus status) {
}
