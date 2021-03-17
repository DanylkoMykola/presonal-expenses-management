package com.danylko.expensesmanagement.entity;

public class TotalExpenses {

    private Double total;
    private String currency;

    public TotalExpenses(String currency) {
        this.total = 0.00;
        this.currency = currency;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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
