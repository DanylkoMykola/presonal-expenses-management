package com.danylko.expensesmanagement.repo;

import com.danylko.expensesmanagement.entity.PersonExpense;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface PersonExpenseRepository extends CrudRepository<PersonExpense, Long> {
    List<PersonExpense> deleteByDate(LocalDate date);
}
