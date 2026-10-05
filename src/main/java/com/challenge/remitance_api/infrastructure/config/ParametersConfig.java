package com.challenge.remitance_api.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.quote")
@Getter
@Setter
public class ParametersConfig {
    private String baseURL;
    private String path;
}
