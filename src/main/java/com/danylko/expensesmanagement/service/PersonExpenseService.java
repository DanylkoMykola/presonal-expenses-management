package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PersonExpenseService {

    void save(PersonExpense personExpense);
    List<PersonExpense> findAll();
    void deleteByDate(LocalDate date);
}
