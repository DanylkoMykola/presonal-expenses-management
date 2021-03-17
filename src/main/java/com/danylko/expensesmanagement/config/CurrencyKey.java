package com.danylko.expensesmanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "currency")
@Configuration
public class CurrencyKey {

    private String converterkey;

    public String getConverterkey() {
        return converterkey;
    }

    public void setConverterkey(String converterkey) {
        this.converterkey = converterkey;
    }
}
