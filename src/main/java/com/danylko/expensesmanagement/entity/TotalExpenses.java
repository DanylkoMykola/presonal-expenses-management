package com.danylko.expensesmanagement.entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TotalExpenses {

    private Double total;
    private String currency;

    public TotalExpenses(String currency) {
        this.total = 0.0;
        this.currency = currency;
    }

    public Double getTotal() {
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        return new Double(df.format(total));
    }

    public void setTotal(Double total) {
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
        this.total = new Double(df.format(total));
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void addTotal(Double total) {
        this.total += total;
    }
}
