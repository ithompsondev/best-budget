package com.bestbudget.money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;

public class Money implements Transactionable {
    // For big decimals that are zero or positive we use a defaul scale of 2 meaning .00
    private static final int ZERO_OR_POSITIVE_DECIMAL_SCALE = 2;
    protected Currency currency;
    protected BigDecimal amount;
    
    public Money() {}
    
    public Money(Currency currency) {
        this.currency = currency;
    }
    
    public Money(Currency currency, BigDecimal initialAmount) {
        this.currency = currency;
        this.amount = initialAmount;
    }
    
    // TODO: Need to add a check to make sure the money is in the same currency. Then update the tests
    /**
     * Add the value of two money objects together, saving the new result in the value of this object.
     * money += otherMoney
     *
     * @param otherMoney Money to be added to this money
     */
    public void add(Money otherMoney) {
        amount = amount.add(otherMoney.getAmount());
    }
    
    /**
     * Subtract the value of two money objects together, saving the new result in the value of this object
     * money -= otherMoney
     *
     * @param otherMoney Money to be subtracted from this money
     */
    public void subtract(Money otherMoney) {
        amount = amount.subtract(otherMoney.getAmount());
    }
    
    /**
     * Multiply the value of this money by some arbitrary integer, saving the result in the value of this object
     * money *= otherMoney
     *
     * @param value an arbitrary integer
     */
    public void multiply(int value) {
        amount = amount.multiply(Money.money(value));
    }
    
    /**
     * Divide the value of this money by some arbitrary integer, saving the result in the value of this object
     * money /= otherMoney
     *
     * @param value an arbitrary integer
     */
    public void divide(int value) {
        amount = amount.divide(Money.money(value));
    }
    
    /**
     * Get the currency of this Money object
     *
     * @return The currency of this money object
     */
    public Currency getCurrency() {
        return currency;
    }
    
    /**
     * Get the currency of this money object as a string
     *
     * @return The currency of this money object as a string
     */
    public String getCurrencyAsString() { return currency.getCurrencyCode(); }
    
    /**
     * Get the currency symbol of this money object as string
     *
     * @return The currency symbol of this money object as a string
     */
    public String getCurrencySymbol() { return currency.getSymbol(); }
    
    /**
     * Set the currency of this money object using an instance of Currency
     *
     * @param currency An instance of the Currency class
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    /**
     * Get the value of this money object. The worth of the money
     *
     * @return The amount of money this money object represents
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * Set the amount of this money object. The worth of the money.
     *
     * @param amount The amount of money this money object represents
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    // TODO: What to do if user inputs a double with 3 or more digits after decimal
    // TODO: Ask Stack if this is viable ?
    
    /**
     * Generate a BigDecimal value with the default scale of 2 that represents
     * how much money this object represents (in a given currency)
     *
     * @param amount The amount of money this money object represents
     * @return The BigDecimal representation of the amount of money
     */
    public static BigDecimal money(double amount) {
        return new BigDecimal(amount).setScale(
                ZERO_OR_POSITIVE_DECIMAL_SCALE,
                RoundingMode.HALF_DOWN
        );
    }
    
    /**
     * Generate a BigDecimal value with the default scale of 2 that represents
     * how much money this object represents (in a given currency)
     *
     * @param amount The amount of money this money object represents
     * @return The BigDecimal representation of the amount of money
     */
    public static BigDecimal money(int amount) {
        return money(amount * 1.0);
    }
    
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        
        Money money = (Money) other;
        return money.getAmount().equals(this.getAmount());
    }
}
