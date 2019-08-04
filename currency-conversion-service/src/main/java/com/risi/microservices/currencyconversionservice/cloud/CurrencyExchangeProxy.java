package com.risi.microservices.currencyconversionservice.cloud;

import com.risi.microservices.currencyconversionservice.domain.ConvertedCurrency;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "netflix-zuul-api-gateway-server")
@FeignClient(name = "spring-cloud-gateway")
//@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange-service/currency-exchange/{from}/{to}")
    ConvertedCurrency getExchangeValue(@PathVariable String from, @PathVariable String to);
}
