package com.bestbudget.budget;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import com.bestbudget.money.Expense;
import com.bestbudget.money.Income;
import com.bestbudget.money.Money;

// TODO: Document me
public class StandardBudget implements Budget {
    private List<Income> incomes;
    private List<Expense> expenses;
    private Income carryOver;
    private Income summedIncomes;
    private Expense summedExpenses;
    private Currency currency;
    
    public StandardBudget(Currency currency) {
        this.currency = currency;
        this.incomes = new ArrayList<Income>();
        this.expenses = new ArrayList<Expense>();
        this.carryOver = new Income(currency,Money.money(0));
        this.summedIncomes = new Income(currency,Money.money(0.0));
        this.summedExpenses = new Expense(currency,Money.money(0.0));
    }
    
    // TODO: Exception handling for incomes < 0
    
    /**
     * Sum all incomes declared within this budget
     */
    @Override
    public void sumAllIncomes() {
        for (Income income: incomes) {
            summedIncomes.add(income);
        }
    }
    
    // TODO: Exception handling for expenses < 0
    /**
     * Sum all expenses declared within this budget
     */
    @Override
    public void sumAllExpenses() {
        for (Expense expense: expenses) {
            summedExpenses.add(expense);
        }
    }
    
    // TODO: Exception handling for summedExpenses > summedIncomes
    
    /**
     * Calculate the difference between incomes and expenses
     */
    @Override
    public void calculateCarryOver() {
        summedIncomes.subtract(summedExpenses);
        carryOver.setAmount(summedIncomes.getAmount());
    }
    
    /**
     * Declare all the incomes for this budget from a predefined list of Income objects
     *
     * @param incomes A list of Income objects
     */
    @Override
    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }
    
    /**
     * Declare all the expenses for this budget from a predefined list of Expense objects
     *
     * @param expenses A list of Expense objects
     */
    @Override
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
    
    /**
     * Declare a single income to be added to this budget
     *
     * @param income An Income object
     */
    @Override
    public void addIncome(Income income) {
        this.incomes.add(income);
    }
    
    /**
     * Declare a single expense to be added to this budget
     *
     * @param expense An expense object
     */
    @Override
    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }
    
    /**
     * Search this budget for an Income object that has a label that matches
     * the given label
     *
     * @param label The search criteria
     * @return The Income object with the matching label
     */
    // TODO: Throw and exception here?
    @Override
    public Income getIncomeByLabel(String label) {
        for (Income income: incomes) {
            if (income.getLabel().equals(label)) {
                return income;
            }
        }
        return null;
    }
    
    // TODO: Throw an exception here?
    /**
     * Search this budget for an Expense object that has a label that matches
     * the given label
     *
     * @param label The search criteria
     * @return The Expense object with the matching label
     */
    @Override
    public Expense getExpenseByLabel(String label) {
        for (Expense expense: expenses) {
            if (expense.getLabel().equals(label)) {
                return expense;
            }
        }
        return null;
    }
    
    /**
     * Get the total value of all incomes declared in this budget
     *
     * @return The total value of all incomes
     */
    @Override
    public Income getTotalIncome() {
        return summedIncomes;
    }
    
    /**
     * Get the total value of all expenses declared in this budget
     *
     * @return The total value of all expenses
     */
    @Override
    public Expense getTotalExpense() {
        return summedExpenses;
    }
    
    // TODO: Observer pattern here? Carry over updates immediately after a new income or expense is added?
    /**
     * Get the value of the expression: total income - total expense
     *
     * @return Money object representing the value of the expression: total income - total expense
     */
    @Override
    public Money getCarryOver() {
        return carryOver;
    }
}
