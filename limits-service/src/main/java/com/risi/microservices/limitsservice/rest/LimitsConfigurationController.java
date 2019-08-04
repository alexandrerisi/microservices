package com.risi.microservices.limitsservice.rest;

import com.risi.microservices.limitsservice.configuration.LimitsServiceConfiguration;
import com.risi.microservices.limitsservice.domain.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private LimitsServiceConfiguration configuration;

    @GetMapping("/limits")
    public LimitConfiguration getLimitsFromConfigurations() {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod = "fallBackConfiguration")
    public LimitConfiguration faultToleranceExample() {
        throw new RuntimeException("Yaics!");
    }

    private LimitConfiguration fallBackConfiguration() {
        return new LimitConfiguration(2, 1);
    }
}
