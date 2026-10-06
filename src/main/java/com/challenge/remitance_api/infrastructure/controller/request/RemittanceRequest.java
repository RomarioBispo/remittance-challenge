package com.challenge.remitance_api.infrastructure.controller.request;


import io.swagger.v3.oas.annotations.media.Schema;



public record RemittanceRequest (
        @Schema(name = "creditorId", example = "1", required = true) String creditorId,
        @Schema(name = "debitorId", example = "2", required = true) String debitorId,
        @Schema(name = "value", example = "100.00", required = true) Double value) {
}
