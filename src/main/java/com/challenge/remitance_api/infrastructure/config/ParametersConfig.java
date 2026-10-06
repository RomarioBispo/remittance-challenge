package com.challenge.remitance_api.infrastructure.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.params")
public record ParametersConfig(
        @NotBlank String baseURL,
        @NotBlank String path,
        @NotNull @Positive Integer naturalPersonDailyLimit,
        @NotNull @Positive Integer legalPersonDailyLimit){
}
