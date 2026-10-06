package com.challenge.remitance_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RemitanceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemitanceApiApplication.class, args);
	}

}
