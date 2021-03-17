package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.repo.PersonExpenseRepository;

import java.util.ArrayList;
import java.util.List;

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
    public void delete(PersonExpense personExpense) {
        repository.delete(personExpense);
    }
}
