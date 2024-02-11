package com.example.countries.service;

import com.example.countries.domain.Country;
import com.example.countries.service.external.CountryAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {
    private static final String ERROR_MESSAGE = "Error when getting country by code";
    private final CountryAPI countryAPI;
    public Country getCountryByCode(String countryCode){
        try {
            if(countryCode.length() > 3){
                ResponseStatusException exception = new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "country code length cannot be larger then 3 characters"
                );
                log.warn(ERROR_MESSAGE, exception);

                throw exception;
            }
            return countryAPI.getCountryByCode(countryCode.toUpperCase());
        } catch (HttpClientErrorException exception) {
            log.warn(ERROR_MESSAGE, exception);
            throw new ResponseStatusException(exception.getStatusCode(), exception.getMessage());
        }
    }
}
