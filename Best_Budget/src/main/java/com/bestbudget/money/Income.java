package com.bestbudget.money;

import java.math.BigDecimal;
import java.util.Currency;


public class Income extends Money {
    private String label;
    
    public Income(Currency currency) {
        super(currency);
    }
    
    public Income(Currency currency, BigDecimal amount) {
        super(currency,amount);
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }
}
