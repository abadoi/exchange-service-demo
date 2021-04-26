package com.andrei.exchange.repository;

import com.andrei.exchange.models.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    List<Currency> findAllByDate(Date date);
    Currency findByDateAndCurrency(Date date, String currency);
    Page<Currency> findAllByCurrency(String currency, Pageable p);
}