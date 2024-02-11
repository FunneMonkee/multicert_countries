package com.example.countries.web;

import com.example.countries.domain.Country;
import com.example.countries.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Country API")
public class ApiCountry {
    private final CountryService countryService;

    @GetMapping(value = "/countries/{countryCode}", produces = "application/json")
    @Operation(summary = "Get a country's properties given a country code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Returns the country's properties",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request - The given country's code is larger than 3 characters",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404",
                    description = "Not Found - Country doesn't exist",
                    content = @Content(mediaType = "application/json"))
    })
    public Country getCountryByCode(@PathVariable("countryCode") String countryCode){
        return countryService.getCountryByCode(countryCode);
    }
}
