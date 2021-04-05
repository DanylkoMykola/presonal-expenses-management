package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.repo.PersonExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Service
@Qualifier("PersonExpenses")
public class PersonExpenseServiceImpl implements PersonExpenseService {

    private final PersonExpenseRepository repository;

    public PersonExpenseServiceImpl(PersonExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean create(PersonExpense personExpense) {
        if (personExpense == null) {
            return false;
        }
        repository.save(personExpense);
        return true;
    }
    @Override
    public List<PersonExpense> findAll() {
        List<PersonExpense> expenses = new ArrayList<>();
        Iterable<PersonExpense> iterable = repository.findAll();
        iterable.forEach(expenses::add);
        return expenses;
    }

    @Override
    public Map<LocalDate, List<PersonExpense>> getSortedByDate() {
        Map<LocalDate, List<PersonExpense>> expenseMap = new TreeMap<>();
        Iterable<PersonExpense> iterable = repository.findAll();

        for (PersonExpense personExpense : iterable) {
            LocalDate date = personExpense.getDate();
            expenseMap.computeIfPresent(date, (key, val) -> {
                val.add(personExpense);
                return val;
            });
            expenseMap.putIfAbsent(date,  new ArrayList<>(Arrays.asList(personExpense)));
        }
        return expenseMap;
    }

    @Transactional
    @Override
    public List<PersonExpense> deleteByDate(String date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            return repository.deleteByDate(localDate);
        } catch (DateTimeParseException e) {
            return new ArrayList<>();
        }

    }
}
