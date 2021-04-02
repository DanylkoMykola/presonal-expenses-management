package com.danylko.expensesmanagement.controller;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.entity.TotalExpenses;
import com.danylko.expensesmanagement.service.PersonExpenseService;
import com.danylko.expensesmanagement.service.TotalExpensesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
public class MainController {

    Logger log = LoggerFactory.getLogger(MainController.class);
    private final PersonExpenseService expenseService;
    private final TotalExpensesService totalExpensesService;

    public MainController(@Qualifier("PersonExpenses") PersonExpenseService expenseService,
                          @Qualifier("TotalExpenses") TotalExpensesService totalExpensesService) {
        this.expenseService = expenseService;
        this.totalExpensesService = totalExpensesService;
    }

    @PostMapping("/expenses")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PersonExpense personExpense) {
        expenseService.create(personExpense);
    }

    @GetMapping("/expenses")
    public Map<LocalDate, List<PersonExpense>> findAll() {
        return expenseService.getSortedByDate();
    }

    @DeleteMapping("/expenses")
    public List<PersonExpense> delete(@RequestParam String date) {
        return expenseService.deleteByDate(date);
    }

    @GetMapping("/total")
    public TotalExpenses getTotalExpenses(@RequestParam String base) {
        List<PersonExpense> expenses = expenseService.getAll();
        return totalExpensesService.getTotalExpenses(expenses, base);
    }
}
