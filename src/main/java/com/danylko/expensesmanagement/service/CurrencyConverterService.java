package com.danylko.expensesmanagement.service;

import com.posadskiy.currencyconverter.enums.Currency;

public interface CurrencyConverterService {
    Double convert(Currency form, Currency to);
}
