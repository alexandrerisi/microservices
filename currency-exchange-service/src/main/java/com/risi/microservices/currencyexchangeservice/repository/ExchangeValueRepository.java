package com.risi.microservices.currencyexchangeservice.repository;

import com.risi.microservices.currencyexchangeservice.domain.ExchangeValue;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExchangeValueRepository extends CrudRepository<ExchangeValue, Integer> {

    Optional<ExchangeValue> findByCurrencyFromAndCurrencyTo(String from, String to);
}
