package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.repo.PersonExpenseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("PersonExpenses")
public class PersonExpenseServiceImpl implements PersonExpenseService {

    private final PersonExpenseRepository repository;

    public PersonExpenseServiceImpl(PersonExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(PersonExpense personExpense) {
        repository.save(personExpense);
    }

    @Override
    public List<PersonExpense> findAll() {
        List<PersonExpense> expenses = new ArrayList<>();
        Iterable<PersonExpense> iterable = repository.findAll();
        iterable.forEach(expenses::add);
        return expenses;
    }

    @Override
    public void deleteByDate(LocalDate date) {
        repository.deleteByDate(date);
    }
}
