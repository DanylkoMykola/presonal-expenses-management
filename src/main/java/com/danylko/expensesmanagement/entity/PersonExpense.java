package com.danylko.expensesmanagement.entity;

import com.posadskiy.currencyconverter.enums.Currency;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Table(name = "expenses")
public class PersonExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private Double amount;

    @Column
    private Currency currency;

    @Column
    private String product;

    public PersonExpense() {
    }

    public PersonExpense(Long id,
                         LocalDate date,
                         Double amount,
                         Currency currency,
                         String product) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        return new Double(df.format(amount));
    }

    public void setAmount(Double amount) {
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        this.amount = new Double(df.format(amount));
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
