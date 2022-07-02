package budget;

import java.util.List;

import com.bestbudget.money.Expense;
import com.bestbudget.money.Income;
import com.bestbudget.money.Money;


public interface Budget {
    public void sumAllIncomes();
    public void sumAllExpenses();
    public void calculateCarryOver();
    public void setIncomes(List<Income> incomes);
    public void setExpenses(List<Expense> expenses);
    public void addIncome(Income income);
    public void addExpense(Expense expense);
    public Income getIncomeByLabel(String label);
    public Expense getExpenseByLabel(String label);
    public Income getTotalIncome();
    public Expense getTotalExpense();
    public Money getCarryOver();
}
