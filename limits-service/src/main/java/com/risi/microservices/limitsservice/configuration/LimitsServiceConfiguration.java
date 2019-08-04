package com.risi.microservices.limitsservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits.service")
@Getter
@Setter
public class LimitsServiceConfiguration {

    private int minimum;
    private int maximum;
}
