package com.challenge.remitance_api.infrastructure.controller.request;

public record RemittanceRequest (String creditorId, String debitorId, Double value) {
}
