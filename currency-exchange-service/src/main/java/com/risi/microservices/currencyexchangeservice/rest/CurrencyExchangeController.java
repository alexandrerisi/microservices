package com.risi.microservices.currencyexchangeservice.rest;

import com.risi.microservices.currencyexchangeservice.service.ExchangeValueService;
import com.risi.microservices.currencyexchangeservice.domain.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.logging.Logger;

@RestController
public class CurrencyExchangeController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private Environment environment;
    @Autowired
    private ExchangeValueService exchangeValueService;

    @GetMapping("/currency-exchange/{from}/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueService.getConversionRate(from, to);
        if (exchangeValue != null)
            exchangeValue.setPort(
                    Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
        logger.info(exchangeValue != null ? exchangeValue.toString() : "null value");
        return exchangeValue;
    }
}
