package com.bestbudget.money;

import java.math.BigDecimal;
import java.util.Currency;

public class Income extends Money {
    private String label;
    
    public Income() {}
    
    public Income(Currency currency) {
        super(currency);
    }
    
    public Income(Currency currency, BigDecimal amount) {
        super(currency,amount);
    }
    
    /**
     * Set the label for this income. The label to be displayed in a budget view
     *
     * @param label The label for this income
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * Set a label for this income immediately after instantiating a new Income object
     * or performing an operation that returns an Income object. The label to be displayed
     * in a budget view
     *
     * @param label The label for this income
     * @return this Income object
     */
    public Income andSetLabel(String label) {
        this.label = label;
        return this;
    }
    
    /**
     * Get the label for this Income object. The label displayed by a budget view
     *
     * @return The label for this Income object
     */
    public String getLabel() {
        return label;
    }
    
    @Override
    public boolean equals(Object other) {
        boolean superComparison = equals(other);
        Income income = (Income) other;
        
        String otherLabel = income.getLabel();
        if (otherLabel == null) {
            return false;
        }
        
        return superComparison && this.label.equals(otherLabel);
    }
}
