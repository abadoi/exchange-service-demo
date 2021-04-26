package com.andrei.exchange.repository;

import com.andrei.exchange.models.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    List<Currency> findAllByDate(Date date);
    Page<Currency> findAllByDateAndCurrency(Date date, String currency, Pageable p);
    List<Currency> findAllByCurrency(String currency);

//    Pageable pageable = PageRequest.of(0, 20, Sort.by("date").descending().and(Sort.by("lastName").descending());
}