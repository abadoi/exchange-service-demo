package com.andrei.exchange.controller;

import java.util.*;

import com.andrei.exchange.model.Currency;
import com.andrei.exchange.service.CurrencyService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currencies")
    public List<Currency> list() {
        return currencyService.listAll();
    }

}
