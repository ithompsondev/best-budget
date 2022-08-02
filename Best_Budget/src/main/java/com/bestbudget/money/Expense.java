package com.bestbudget.money;

import java.math.BigDecimal;
import java.util.Currency;

public class Expense extends Money {
    private String label;
    
    public Expense() {}
    
    public Expense(Currency currency) {
        super(currency);
    }
    
    public Expense(Currency currency, BigDecimal amount) {
        super(currency,amount);
    }
    
    /**
     * Set the label for this expense. The label to be displayed in a budget view
     *
     * @param label The label for this expense
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Set a label for this expense immediately after instantiating a new Expense object
     * or performing an operation that returns an Expense object. The label to be displayed
     * in a budget view
     *
     * @param label The label for this expense
     * @return this Expense object
     */
    public Expense andSetLabel(String label) {
        this.label = label;
        return this;
    }
    
    /**
     * Get the label for this Expense object. The label displayed by a budget view
     *
     * @return The label for this Expense object
     */
    public String getLabel() {
        return label;
    }
    
    @Override
    public boolean equals(Object other) {
        boolean superComparison = equals(other);
        Expense expense = (Expense) other;
        
        String otherLabel = expense.getLabel();
        if (otherLabel == null) {
            return false;
        }
        
        return superComparison && this.label.equals(otherLabel);
    }
}
