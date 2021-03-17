package com.danylko.expensesmanagement.service;

import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import com.posadskiy.currencyconverter.enums.Currency;
import org.springframework.beans.factory.annotation.Value;

public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    @Value("${currency.converter-key}")
    private String converterKey;
    private CurrencyConverter converter;

    public CurrencyConverterServiceImpl() {
        this.converter = new CurrencyConverter(
                new ConfigBuilder()
                        .currencyConverterApiApiKey(converterKey)
                        .build());
    }

    @Override
    public Double convert(Currency form, Currency to) {
        return converter.rate(form, to);
    }
}
