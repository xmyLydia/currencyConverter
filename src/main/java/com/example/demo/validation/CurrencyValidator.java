package com.example.demo.validation;

import com.example.demo.exceptions.RestNotFoundException;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.io.IOException;
import java.util.List;

/**
 * @author mingyux
 */
@Component
public class CurrencyValidator {
    @Autowired
    CurrencyService currencyService;

    public void validate(String url, String source, String target, int amount, BindException error) throws IOException,
            RestNotFoundException {
        if (amount <= 0) {
            error.reject("amount", "monetary amount should be more than 0");
        }
        List<String> availableCurrency = currencyService.getAvailableCurrencies(url);
        if (!availableCurrency.contains(source)) {
            error.reject("source", "currency: " + source + " is not available");
        }
        if (!availableCurrency.contains(target)) {
            error.reject("target", "currency: " + target + " is not available");
        }
    }
}
