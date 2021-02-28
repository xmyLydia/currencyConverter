package com.example.demo.service;

import com.example.demo.common.HTTPCurrencyRequest;
import com.example.demo.common.RestUtils;
import com.example.demo.entity.ConvertEntity;
import com.example.demo.exceptions.RestNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author mingyux
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    HTTPCurrencyRequest httpCurrencyRequest;

    @Override
    public float convertCurrency(String url, String source, String target, int amount)
            throws IOException, RestNotFoundException {

        String currencyAPI = url + source + ',' + target;
        String jsonStr = httpCurrencyRequest.httpCurrencyFetching(currencyAPI);
        ObjectMapper objectMapper = new ObjectMapper();
        ConvertEntity convertEntity = objectMapper.readValue(jsonStr,
                new TypeReference<ConvertEntity>() {
                });
        RestUtils.notNull(convertEntity);
        if (convertEntity.getRates() != null) {
            Float sourceRate = convertEntity.getRates().get(source);
            Float targetRate = convertEntity.getRates().get(target);
            if (targetRate != 0) {
                return amount / targetRate * sourceRate;
            }
        }
        return -1;
    }

    @Override
    public List<String> getAvailableCurrencies(String url) throws IOException, RestNotFoundException {
        String jsonStr = httpCurrencyRequest.httpCurrencyFetching(url);
        ObjectMapper objectMapper = new ObjectMapper();
        ConvertEntity convertEntity = objectMapper.readValue(jsonStr, new TypeReference<ConvertEntity>() {
        });
        RestUtils.notNull(convertEntity.getRates());
        Map<String, Float> currencyMap = convertEntity.getRates();
        return new ArrayList<>(currencyMap.keySet());
    }
}
