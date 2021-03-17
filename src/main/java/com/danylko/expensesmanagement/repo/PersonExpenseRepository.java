package com.danylko.expensesmanagement.repo;

import com.danylko.expensesmanagement.entity.PersonExpense;
import org.springframework.data.repository.CrudRepository;

public interface PersonExpenseRepository extends CrudRepository<PersonExpense, Long> {
}
