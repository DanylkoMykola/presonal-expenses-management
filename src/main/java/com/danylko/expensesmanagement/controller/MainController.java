package com.danylko.expensesmanagement.controller;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.entity.TotalExpenses;
import com.danylko.expensesmanagement.service.CurrencyConverterService;
import com.danylko.expensesmanagement.service.PersonExpenseService;
import com.posadskiy.currencyconverter.enums.Currency;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    private final PersonExpenseService expenseService;
    private final CurrencyConverterService converterService;

    public MainController(PersonExpenseService expenseService, CurrencyConverterService converterService) {
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

    @DeleteMapping("/expenses/{date}")
    public void delete(@PathVariable LocalDate date) {
        expenseService.deleteByDate(date);
    }

    @GetMapping("/total/{base}")
    public TotalExpenses getTotalExpenses(@PathVariable String base) {
        TotalExpenses totalExpenses = new TotalExpenses(base);
        List<PersonExpense> expenses = expenseService.findAll();
        for (PersonExpense expense : expenses) {
            if (!expense.getCurrency().equals(base)) {
                Double currency = converterService.convert(expense.getCurrency(), base);
                totalExpenses.addTotal(expense.getAmount() * currency);
            }
            else {
                totalExpenses.addTotal(expense.getAmount());
            }
        }
    }
}
