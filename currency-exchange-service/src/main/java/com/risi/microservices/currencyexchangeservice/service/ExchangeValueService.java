package com.risi.microservices.currencyexchangeservice.service;

import com.risi.microservices.currencyexchangeservice.domain.ExchangeValue;
import com.risi.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeValueService {

    @Autowired
    private ExchangeValueRepository repository;

    public ExchangeValue getConversionRate(String from, String to) {
        Optional<ExchangeValue> exchangeValue = repository.findByCurrencyFromAndCurrencyTo(from, to);
        return exchangeValue.orElse(null);
    }
}
