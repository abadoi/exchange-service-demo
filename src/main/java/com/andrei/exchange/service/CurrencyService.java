package com.andrei.exchange.service;

import com.andrei.exchange.model.Currency;
import com.andrei.exchange.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
}