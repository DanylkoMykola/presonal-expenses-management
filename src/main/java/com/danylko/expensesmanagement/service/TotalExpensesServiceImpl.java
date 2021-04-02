package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.entity.TotalExpenses;
import com.posadskiy.currencyconverter.enums.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Qualifier("TotalExpenses")
public class TotalExpensesServiceImpl implements TotalExpensesService {

    Logger log = LoggerFactory.getLogger(TotalExpensesServiceImpl.class);
    private final CurrencyConverterService converterService;

    public TotalExpensesServiceImpl( @Qualifier("FreeCurrencyConverter") CurrencyConverterService converterService) {
        this.converterService = converterService;
    }

    @Override
    public TotalExpenses getTotalExpenses(List<PersonExpense> expenses, String base) {
        TotalExpenses totalExpenses;
        if (checkValidCurrency(base)) {
            Currency currencyBase = Currency.valueOf(base.toUpperCase());
            totalExpenses = new TotalExpenses(currencyBase);
            for (PersonExpense expense : expenses) {
                if (expense.getCurrency() != currencyBase) {
                    Double currency = converterService.convert(expense.getCurrency(), currencyBase);
                    totalExpenses.addTotal(expense.getAmount() * currency);
                }
                else {
                    totalExpenses.addTotal(expense.getAmount());
                }
            }
        }
        else {
            totalExpenses = new TotalExpenses(null);
        }
        return totalExpenses;
    }

    private boolean checkValidCurrency(String base) {
        return Arrays.stream(Currency.values()).anyMatch(v -> v.name().equals(base.toUpperCase()));
    }
}
