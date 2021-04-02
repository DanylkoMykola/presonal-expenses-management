package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public interface PersonExpenseService {

    boolean create(PersonExpense personExpense);
    List<PersonExpense> getAll();
    Map<LocalDate, List<PersonExpense>> getSortedByDate();
    List<PersonExpense> deleteByDate(String date);

}
