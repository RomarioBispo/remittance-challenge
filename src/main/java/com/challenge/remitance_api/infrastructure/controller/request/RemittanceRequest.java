package com.challenge.remitance_api.infrastructure.controller.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record RemittanceRequest (
        @Schema(name = "creditorId", example = "1", required = true) @NotNull String creditorId,
        @Schema(name = "debitorId", example = "2", required = true) @NotNull String debitorId,
        @Schema(name = "value", example = "100.00", required = true)
        @Positive(message = "value must be greater than zero") Double value) {
}
