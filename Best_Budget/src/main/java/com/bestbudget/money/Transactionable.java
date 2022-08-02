package com.bestbudget.money;

/**
 * Define a set of actions common to a financial transaction
 */
public interface Transactionable {
    public void add(Money money);
    public void subtract(Money money);
    public void multiply(int value);
    public void divide(int value);
}
