package com.andrei.exchange.service;

import com.andrei.exchange.models.Currency;
import com.andrei.exchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> listAll() {
        return currencyRepository.findAll();
    }

    public void saveCurrency(Currency c) {
        currencyRepository.save(c);
    }

    public Currency getCurrency(Integer id) {
        return currencyRepository.findById(id).get();
    }

    public void deleteCurrency(Integer id) {
        currencyRepository.deleteById(id);
    }

    public void saveCurrencyList(List<Currency> currencyList) {
        if(currencyRepository.findAllByDate(currencyList.get(0).getDate()).size() == 0) {
            currencyRepository.saveAll(currencyList);
        }
    }

    public List<Currency> getAllByDate(Date date){
        return currencyRepository.findAllByDate(date);
    }

    public Currency getByDateAndCurrency (Date date, String currency){
        return currencyRepository.findByDateAndCurrency(date, currency);
    }

    public Page<Currency> getAllByCurrency (String currency, Pageable p){
        return currencyRepository.findAllByCurrency(currency, p);
    }

}