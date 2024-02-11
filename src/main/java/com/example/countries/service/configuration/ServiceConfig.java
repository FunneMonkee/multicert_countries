package com.example.countries.service.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfig {
    @Bean("restTemplate")
    public RestTemplate restTemplateBuilder() {
        return new RestTemplateBuilder().build();
    }
}
