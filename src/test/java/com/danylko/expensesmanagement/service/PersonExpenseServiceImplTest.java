package com.danylko.expensesmanagement.service;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.repo.PersonExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

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
        boolean isCreated = expenseService.create(personExpense);
        assertTrue(isCreated);
        verify(repository, times(1)).save(personExpense);
    }

    @Test
    void deleteByDateTest() {
        LocalDate date = LocalDate.now();
        List<PersonExpense> expenseList = expenseService.deleteByDate(date);
        assertNotNull(expenseList);
        verify(repository, times(1)).deleteByDate(date);
    }
}