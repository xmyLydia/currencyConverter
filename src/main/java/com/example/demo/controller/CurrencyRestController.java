package com.example.demo.controller;

import com.example.demo.entity.ConvertEntity;
import com.example.demo.service.CurrencyService;
import com.example.demo.validation.CurrencyValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mingyux
 */
@RestController
public class CurrencyRestController {
    private static final String CURRENCY_BASE = "https://api.exchangeratesapi.io/latest?symbols=";
    private static final String AVAILABLE_CURRENCIES = "https://api.exchangeratesapi.io/latest";

    Logger logger = LoggerFactory.getLogger(CurrencyRestController.class);

    @Autowired
    CurrencyService currencyService;

    @Autowired
    CurrencyValidator currencyValidator;

    @GetMapping("/convert")
    public String convert(@RequestParam("source") String source,
                          @RequestParam("target") String target,
                          @RequestParam("amount") int amount) {

        try {
            BindException bindException = new BindException(new ConvertEntity(), "convertEntity");
            currencyValidator.validate(AVAILABLE_CURRENCIES, source, target, amount, bindException);
            if (bindException.hasErrors()) {
                for (ObjectError error : bindException.getAllErrors()) {
                    logger.error(error.toString());
                }
                throw new BindException(bindException);
            }
            float targetValue = currencyService.convertCurrency(CURRENCY_BASE, source, target, amount);
            return String.valueOf(targetValue);
        } catch (BindException bindException) {
            return bindException.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
