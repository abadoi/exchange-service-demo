package com.andrei.exchange.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.andrei.exchange.models.Cube;
import com.andrei.exchange.models.Currency;
import com.andrei.exchange.models.Envelope;
import com.andrei.exchange.service.CurrencyService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Pageable;


@RestController
public class CurrencyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyService currencyService;

    @ResponseBody
    @GetMapping("/date")
    public List<Currency> getAllCurrencyByDate(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        List<Currency> result = currencyService.getAllByDate(date);
        return result;
    }


    @ResponseBody
    @GetMapping("/currency")
    public Currency getCurrency(@RequestParam(value = "date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date, @RequestParam(value = "currency") String currency) {
        Currency result = currencyService.getByDateAndCurrency(date, currency);
        return result;
    }

    @ResponseBody
    @GetMapping("/currency/all")
    public List<Currency> getAllCurrency(@PageableDefault(value=10, page=0) Pageable pageable, @RequestParam(value = "currency") String currency) {
        // sort by date in descending order
        Pageable newPageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("date").descending());
        Page page = currencyService.getAllByCurrency(currency, newPageRequest);

        // prevent requesting a non-existing page
        if(page.getTotalElements() > pageable.getPageNumber())
            return page.getContent();

        return new ArrayList<>();
    }

    @GetMapping("/fetch")
    public String fetch() {
        String xmlData = restTemplate.getForObject("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml", String.class);
        InputStream is = new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8));

        XmlMapper xmlMapper = new XmlMapper();
        Envelope envelope = new Envelope();

        try {

            envelope = xmlMapper.readValue(is, Envelope.class);

        } catch (IOException e){
            System.out.println("Error parsing XML to Envelope" + e.getMessage());
        }

        List<Currency> currencyList = new ArrayList<>();
        Date date = envelope.getCubeWrapper().getCubes().getTime();
        for (Cube cube : envelope.getCubeWrapper().getCubes().getCubes()){
            Currency newCurrency = new Currency(cube.getCurrency(), cube.getRate(), date);
            currencyList.add(newCurrency);
        }

        currencyService.saveCurrencyList(currencyList);

        return currencyList.size() + " currencies updated.";
    }

}
