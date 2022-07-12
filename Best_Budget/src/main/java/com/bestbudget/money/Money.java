package com.bestbudget.money;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;

// TODO: Document me
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
    
    public void add(Money otherMoney) {
        amount = amount.add(otherMoney.getAmount());
    }
    
    public void subtract(Money otherMoney) {
        amount = amount.subtract(otherMoney.getAmount());
    }
    
    public void multiply(int value) {
        amount = amount.multiply(new BigDecimal(value));
    }
    
    public void divide(int value) {
        amount = amount.divide(new BigDecimal(value));
    }
    
    public Currency getCurrency() {
        return currency;
    }
    
    public String getCurrencyAsString() { return currency.getCurrencyCode(); }
    
    public String getCurrencySymbol() { return currency.getSymbol(); }
    
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    // Static Util and Factory methods
    
    // Allows us to automatically generate BigDecimals with a scale of 2
    // TODO: What to do if user inputs a double with 3 or more digits after decimal
    // TODO: Ask Stack if this is viable ?
    public static BigDecimal money(double amount) {
        return new BigDecimal(amount)
                .setScale(ZERO_OR_POSITIVE_DECIMAL_SCALE, RoundingMode.HALF_DOWN);
    }
    
    public static BigDecimal money(int amount) {
        return money(amount * 1.0);
    }
    
    // TODO: Improve this
    @Override
    public boolean equals(Object other) {
        Money money = (Money) other;
        return money.getAmount().equals(this.getAmount());
    }
}
