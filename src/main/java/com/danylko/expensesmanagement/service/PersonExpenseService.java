package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface PersonExpenseService {

    boolean save(PersonExpense personExpense);
    List<PersonExpense> findAll();
    boolean deleteByDate(LocalDate date);
}
