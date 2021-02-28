package com.example.demo.service;

import com.example.demo.exceptions.RestNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author mingyux
 */
public interface CurrencyService {
    /**
     * This method will calculate the target monetary value after input
     * source, target currency and source monetary value
     *
     * @param url fetching url
     * @param source source currency
     * @param target target currency
     * @param amount monetary value of source currency
     * @return monetary value of target currency
     * @throws IOException
     */
    float convertCurrency(String url, String source, String target, int amount) throws IOException, RestNotFoundException;

    /**
     * This method fetch all of the available currencies
     *
     * @param url fetching url
     * @return list of available currencies
     * @throws IOException
     * @throws RestNotFoundException
     */
    List<String> getAvailableCurrencies(String url) throws IOException, RestNotFoundException;
}
