package com.example.demo.service;

import com.example.demo.exceptions.RestNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceImplTest {
    @Autowired
    CurrencyService currencyService;

    @Test
    void convertCurrencyWithNotExistingCurrencyShouldThrowException() {
        String urlBase = "https://api.exchangeratesapi.io/latest?symbols=";
        String notExistingCurrency = "test";
        String url = urlBase + notExistingCurrency + ",USD";
        Assertions.assertThrows(IOException.class, () -> {
            currencyService.convertCurrency(url, notExistingCurrency, "USD", 200);
        });
    }

    @Test
    void convertCurrencyWithExistingCurrencyAndAmountShouldReturnPositiveNumber() throws IOException,
            RestNotFoundException {
        String urlBase = "https://api.exchangeratesapi.io/latest?symbols=";
        String existingCurrency = "CNY";
        assertTrue(currencyService.convertCurrency(urlBase, existingCurrency, "USD", 200) > -1);
    }

    @Test
    void getAvailableCurrenciesWithWrongURLShouldThrowException() {
        String urlMalFormed = "wrong_url";
        Assertions.assertThrows(MalformedURLException.class, () -> {
            currencyService.getAvailableCurrencies(urlMalFormed);
        });
        String urlWrongAddress = "https://api.exchangeratesapi.io/wrongaddress";
        Assertions.assertThrows(IOException.class, () -> {
            currencyService.getAvailableCurrencies(urlWrongAddress);
        });
    }

    @Test
    void getAvailableCurrenciesWithCorrectURLShouldReturnListOfCurrency() throws IOException, RestNotFoundException {
        String AVAILABLE_CURRENCIES = "https://api.exchangeratesapi.io/latest";
        List<String> currencies = currencyService.getAvailableCurrencies(AVAILABLE_CURRENCIES);
        assertNotNull(currencies);
        assertThat(currencies, hasSize(greaterThan(0)));
    }
}