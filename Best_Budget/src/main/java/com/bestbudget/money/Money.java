package com.bestbudget.money;

import java.math.BigDecimal;
import java.util.Currency;


public class Money implements Transactionable {
    protected Currency currency;
    protected BigDecimal amount;
    
    public Money(Currency currency) {
        this.currency = currency;
    }
    
    public Money(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
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
    
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    // TODO: Improve this
    @Override
    public boolean equals(Object other) {
        Money money = (Money) other;
        return money.getAmount().equals(this.getAmount());
    }
}
