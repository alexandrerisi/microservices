package com.risi.microservices.currencyconversionservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvertedCurrency {

    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionRate;
    private BigDecimal quantity;
    private BigDecimal convertedAmount;
    private int port;
}
