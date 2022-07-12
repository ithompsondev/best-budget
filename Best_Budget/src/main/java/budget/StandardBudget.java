package budget;

import java.math.BigDecimal;
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
        
        incomes = new ArrayList<Income>();
        expenses = new ArrayList<Expense>();
        carryOver = new Income(currency,Money.money(0));
    }
    
    // TODO: Exception handling for incomes < 0
    @Override
    public void sumAllIncomes() {
        summedIncomes = new Income(currency,Money.money(0.0));
        for (Income income: incomes) {
            summedIncomes.add(income);
        }
    }
    
    // TODO: Exception handling for expenses < 0
    @Override
    public void sumAllExpenses() {
        summedExpenses = new Expense(currency,Money.money(0.0));
        for (Expense expense: expenses) {
            summedExpenses.add(expense);
        }
    }
    
    // TODO: Exception handling for summedExpenses > summedIncomes
    @Override
    public void calculateCarryOver() {
        summedIncomes.subtract(summedExpenses);
        carryOver.setAmount(summedIncomes.getAmount());
    }
    
    @Override
    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }
    
    @Override
    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
    
    @Override
    public void addIncome(Income income) {
        this.incomes.add(income);
    }
    
    @Override
    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }
    
    @Override
    public Income getIncomeByLabel(String label) {
        Income result = null;
        for (Income income: incomes) {
            if (income.getLabel().equals(label)) {
                return income;
            }
        }
        return null;
    }
    
    @Override
    public Expense getExpenseByLabel(String label) {
        Expense result = null;
        for (Expense expense: expenses) {
            if (expense.getLabel().equals(label)) {
                return expense;
            }
        }
        return null;
    }
    
    @Override
    public Income getTotalIncome() {
        return summedIncomes;
    }
    
    @Override
    public Expense getTotalExpense() {
        return summedExpenses;
    }
    
    @Override
    public Money getCarryOver() {
        return carryOver;
    }
}
