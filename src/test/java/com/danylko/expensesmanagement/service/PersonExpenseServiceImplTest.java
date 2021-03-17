package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.repo.PersonExpenseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
class PersonExpenseServiceImplTest {

    @Autowired
    @Qualifier("PersonExpenses")
    private PersonExpenseService expenseService;

    @MockBean
    private PersonExpenseRepository repository;

    @Test
    void createTest() {
        PersonExpense personExpense = new PersonExpense();
        boolean isCreated = expenseService.save(personExpense);
        Assert.assertTrue(isCreated);
        Mockito.verify(repository, Mockito.times(1)).save(personExpense);
    }

    @Test
    void deleteByDateTest() {
        LocalDate date = LocalDate.now();
        boolean isDeleted = expenseService.deleteByDate(date);
        Assert.assertTrue(isDeleted);
        Mockito.verify(repository, Mockito.times(1)).deleteByDate(date);
    }
}