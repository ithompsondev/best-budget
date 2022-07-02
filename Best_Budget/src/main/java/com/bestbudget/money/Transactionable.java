package com.bestbudget.money;

// TODO: Return BigDecimals as a side effect
public interface Transactionable {
    public void add(Money money);
    public void subtract(Money money);
    public void multiply(int value);
    public void divide(int value);
}
