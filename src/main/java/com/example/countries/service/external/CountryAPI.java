package com.example.countries.service.external;

import com.example.countries.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@RequiredArgsConstructor
public class CountryAPI {
    @Value("${apiBaseURL}")
    private String apiBaseUrl;
    private final RestTemplate restTemplate;
    public Country getCountryByCode(String countryCode) {

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(apiBaseUrl)
                .path("countries/")
                .path(countryCode)
                .queryParam("fields", String.join(",", Country.getJsonPropertyNames()));

        return restTemplate.getForObject(builder.encode().toUriString(), Country.class);
    }

}
