package com.example.countries.service;

import com.example.countries.domain.Country;
import com.example.countries.service.external.CountryAPI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CountryServiceTest {
    @Mock
    private CountryAPI countryAPI;
    @InjectMocks
    private CountryService countryService;
    private static Country country;
    @BeforeAll
    static void setUp(){
        country = new Country();
        country.setCoordinates(new Float[]{1f,1f});
    }
    @Test
    void whenCountryCodeIsLargerThan3Chars_ThenThrowBadRequestException(){
        String expectedReason = "country code length cannot be larger then 3 characters";
        HttpStatusCode expectedCode = HttpStatusCode.valueOf(400);
        when(countryAPI.getCountryByCode(anyString())).thenReturn(country);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,() -> countryService.getCountryByCode("1234"));

        String actualMessage = exception.getReason();
        HttpStatusCode actualCode = exception.getStatusCode();

        assertEquals(actualMessage, expectedReason);
        assertEquals(actualCode, expectedCode);
    }
    @Test
    void whenCountryCodeIsNotFound_ThenThrowNotFoundException(){
        String expectedReason = "404 country not found";
        HttpStatusCode expectedCode = HttpStatusCode.valueOf(404);
        when(countryAPI.getCountryByCode(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "country not found"));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,() -> countryService.getCountryByCode("404"));

        String actualMessage = exception.getReason();
        HttpStatusCode actualCode = exception.getStatusCode();

        assertEquals(actualMessage, expectedReason);
        assertEquals(actualCode, expectedCode);
    }
}