package com.risi.microservices.currencyconversionservice.rest;

import com.risi.microservices.currencyconversionservice.cloud.CurrencyExchangeProxy;
import com.risi.microservices.currencyconversionservice.domain.ConvertedCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class CurrencyConverterController {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private CurrencyExchangeProxy exchangeProxy;
    @Autowired
    private Environment environment;

    /**
     * This is an example of how to not approach micro services!
     */
    @GetMapping("/currency-converter/{from}/{to}/{quantity}")
    public ConvertedCurrency convertCurrency(@PathVariable String from,
                                             @PathVariable String to,
                                             @PathVariable BigDecimal quantity) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<ConvertedCurrency> response = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/{from}/{to}",
                ConvertedCurrency.class,
                uriVariables);
        ConvertedCurrency convertedCurrency = response.getBody();
        return new ConvertedCurrency(convertedCurrency.getCurrencyFrom(),
                convertedCurrency.getCurrencyTo(),
                convertedCurrency.getConversionRate(),
                quantity,
                quantity.multiply(convertedCurrency.getConversionRate()),
                convertedCurrency.getPort());
    }

    /**
     * This is how it should be done!
     */
    @GetMapping("/currency-converter-feign/{from}/{to}/{quantity}")
    public ConvertedCurrency convertCurrencyFeign(@PathVariable String from,
                                             @PathVariable String to,
                                             @PathVariable BigDecimal quantity) {

        ConvertedCurrency convertedCurrency = exchangeProxy.getExchangeValue(from, to);

        if (convertedCurrency != null) {
            logger.info(convertedCurrency.toString());
            return new ConvertedCurrency(convertedCurrency.getCurrencyFrom(),
                    convertedCurrency.getCurrencyTo(),
                    convertedCurrency.getConversionRate(),
                    quantity,
                    quantity.multiply(convertedCurrency.getConversionRate()),
                    convertedCurrency.getPort());
        }
        return null;
    }
}
