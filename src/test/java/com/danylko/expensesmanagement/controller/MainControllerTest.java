package com.danylko.expensesmanagement.controller;

import com.danylko.expensesmanagement.entity.PersonExpense;
import com.danylko.expensesmanagement.entity.TotalExpenses;
import com.danylko.expensesmanagement.service.CurrencyConverterService;
import com.danylko.expensesmanagement.service.PersonExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.posadskiy.currencyconverter.enums.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import java.time.LocalDate;


@WebMvcTest(controllers = MainController.class)
@ActiveProfiles("test")
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    @Qualifier("PersonExpenses")
    private PersonExpenseService expenseService;

    @MockBean
    @Qualifier("FreeCurrencyConverter")
    private CurrencyConverterService converterService;

    private List<PersonExpense> expenses = new ArrayList<>();

    @BeforeEach
    void setUp() {
        this.expenses.add(new PersonExpense(1L, LocalDate.parse("2021-04-25"), 2.85, Currency.USD,  "Jogurt"));
        this.expenses.add(new PersonExpense(2L, LocalDate.parse("2021-04-22"), 12.0, Currency.USD,  "Salmon"));
        this.expenses.add(new PersonExpense(3L, LocalDate.parse("2021-04-25"), 3.0, Currency.UAH,  "French fries"));
    }
    @Test
    void shouldCreateNewPersonExpense() throws Exception {
        PersonExpense personExpense = new PersonExpense(4L, LocalDate.parse("2021-04-27"), 25.5, Currency.UAH,  "Sweets");
        given(expenseService.create(personExpense)).willReturn(true);
        this.mockMvc.perform(post("/expenses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personExpense)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAllPersonExpenses() throws Exception {
        given(expenseService.getAll()).willReturn(expenses);

        this.mockMvc.perform(get("/expenses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(expenses.size())));
    }

    @Test
    void deleteNotExistPersonsExpenses() throws Exception {
        LocalDate date = LocalDate.parse("2021-04-10");
        given(expenseService.deleteByDate(date)).willReturn(new ArrayList<>());
        this.mockMvc.perform(delete("/expenses")
                .param("date", date.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void getTotalExpenses() throws Exception {
        TotalExpenses total = new TotalExpenses(Currency.UAH);
        mockMvc.perform(get("/total")
                .param("base", String.valueOf(Currency.UAH)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(total.getTotal())))
                .andExpect(jsonPath("$.currency", is(String.valueOf(total.getCurrency()))));
    }
}