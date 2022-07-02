package com.bestbudget.money;

import java.math.BigDecimal;
import java.util.Currency;

public class Expense extends Money {
    private String label;
    
    public Expense(Currency currency) {
        super(currency);
    }
    
    public Expense(Currency currency, BigDecimal amount) {
        super(currency,amount);
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
}
