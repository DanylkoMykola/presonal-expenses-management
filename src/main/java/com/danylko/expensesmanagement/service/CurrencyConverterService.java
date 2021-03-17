package com.danylko.expensesmanagement.service;

import com.posadskiy.currencyconverter.enums.Currency;
import org.springframework.stereotype.Service;

@Service
public interface CurrencyConverterService {
    Double convert(Currency form, Currency to);
    Double convert(String form, String to);
}
