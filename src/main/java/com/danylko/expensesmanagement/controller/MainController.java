package com.danylko.expensesmanagement.controller;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.entity.TotalExpenses;
import com.danylko.expensesmanagement.service.CurrencyConverterService;
import com.danylko.expensesmanagement.service.PersonExpenseService;
import com.posadskiy.currencyconverter.enums.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class MainController {

    Logger log = LoggerFactory.getLogger(MainController.class);
    private final PersonExpenseService expenseService;
    private final CurrencyConverterService converterService;

    public MainController(@Qualifier("PersonExpenses") PersonExpenseService expenseService,
                          @Qualifier("FreeCurrencyConverter") CurrencyConverterService converterService) {
        this.expenseService = expenseService;
        this.converterService = converterService;
    }

    @PostMapping("/expenses")
    public void create(@RequestBody PersonExpense personExpense) {
        expenseService.save(personExpense);
    }

    @GetMapping("/expenses")
    public List<PersonExpense> findAll() {
        return expenseService.findAll();
    }

    @DeleteMapping("/expenses")
    public void delete(@RequestParam String date) {
        expenseService.deleteByDate(LocalDate.parse(date));
    }

    @GetMapping("/total")
    public TotalExpenses getTotalExpenses(@RequestParam String base) {
        Currency currencyBase = Currency.valueOf(base);
        TotalExpenses totalExpenses = new TotalExpenses(currencyBase);
        List<PersonExpense> expenses = expenseService.findAll();
        for (PersonExpense expense : expenses) {
            if (expense.getCurrency() != currencyBase) {
                Double currency = converterService.convert(expense.getCurrency(), currencyBase);
                totalExpenses.addTotal(expense.getAmount() * currency);
            }
            else {
                totalExpenses.addTotal(expense.getAmount());
            }
        }

        return totalExpenses;
    }
}
