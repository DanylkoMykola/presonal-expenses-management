package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.config.CurrencyKey;
import com.posadskiy.currencyconverter.CurrencyConverter;
import com.posadskiy.currencyconverter.config.ConfigBuilder;
import com.posadskiy.currencyconverter.enums.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.*;

@Service
@Qualifier("FreeCurrencyConverter")
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    Logger log = LoggerFactory.getLogger(CurrencyConverterServiceImpl.class);

    private final CurrencyKey currencyKey;
    private CurrencyConverter converter;

    public CurrencyConverterServiceImpl(CurrencyKey currencyKey) {
        this.currencyKey = currencyKey;
        log.info(currencyKey.getConverterkey() + "!!!!!!!!!!!!");
        this.converter = new CurrencyConverter(
                new ConfigBuilder()
                        .currencyConverterApiApiKey(currencyKey.getConverterkey())
                        .build());

    }

    @Override
    public Double convert(Currency form, Currency to) {
        return converter.rate(form, to);
    }
    @Override
    public Double convert(String form, String to) {
        return converter.rate(form, to);
    }

}
