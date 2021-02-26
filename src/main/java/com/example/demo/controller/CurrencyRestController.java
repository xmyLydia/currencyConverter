package com.example.demo.controller;

import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mingyux
 */
@RestController
public class CurrencyRestController {
    @Autowired
    CurrencyService currencyService;

    @GetMapping("/convert")
    public String convert( @RequestParam( "source" ) int source,
                           @RequestParam( "target" ) int target,
                           @RequestParam( "amount" ) int amount) {
        return currencyService.convertCurrency(source, target, amount);
    }

}
