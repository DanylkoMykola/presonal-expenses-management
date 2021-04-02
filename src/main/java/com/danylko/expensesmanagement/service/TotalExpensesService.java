package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.entity.TotalExpenses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TotalExpensesService {
    TotalExpenses getTotalExpenses(List<PersonExpense> expenses, String base);
}
