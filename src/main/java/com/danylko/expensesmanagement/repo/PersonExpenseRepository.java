package com.danylko.expensesmanagement.repo;

import com.danylko.expensesmanagement.entity.PersonExpense;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface PersonExpenseRepository extends CrudRepository<PersonExpense, Long> {
    void removeByDate(LocalDate date);
}
