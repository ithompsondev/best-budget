package money;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.Test;

import budget.Budget;
import budget.StandardBudget;
import com.bestbudget.money.Expense;
import com.bestbudget.money.Income;
import com.bestbudget.money.Money;


public class BudgetTest {
    private static final String ZAR_TEST_CURRENCY = "ZAR";
    private static final Currency ZAR = Currency.getInstance(ZAR_TEST_CURRENCY);
    private static final List<Income> incomes = Arrays.asList(new Income[] {
            new Income(ZAR,Money.money(10)),
            new Income(ZAR,Money.money(20.01)),
            new Income(ZAR,Money.money(30.02)),
            new Income(ZAR,Money.money(40.03)),
            new Income(ZAR,Money.money(50.05))
    });
    private static final List<Expense> expenses = Arrays.asList(new Expense[] {
            new Expense(ZAR,Money.money(9.00)),
            new Expense(ZAR,Money.money(19.01)),
            new Expense(ZAR,Money.money(29.02)),
            new Expense(ZAR,Money.money(39.03)),
            new Expense(ZAR,Money.money(49.05))
    });
    
    // TODO: Fix, why is equality not being tested correctly
    @Test
    void testAddAllIncomesOfTheSameCurrency() {
        Budget budget = new StandardBudget(ZAR);
        budget.setIncomes(incomes);
        
        Income expectedSum = new Income(ZAR,Money.money(0));
        expectedSum.setAmount(Money.money(150.11));
        
        budget.sumAllIncomes();
        assertEquals(expectedSum,budget.getTotalIncome());
    }
    
    // TODO: Fix, why is equality not being tested correctly
    @Test
    void testAddAllExpensesOfTheSameCurrency() {
        Budget budget = new StandardBudget(ZAR);
        budget.setExpenses(expenses);
    
        Expense expectedSum = new Expense(ZAR,Money.money(145.11));
    
        budget.sumAllExpenses();
        assertEquals(expectedSum,budget.getTotalExpense());
    }
    
    @Test
    void testCorrectCarryOverCalculationFromSameCurrencyIncomesAndExpenses() {
        Budget budget = new StandardBudget(ZAR);
        budget.setExpenses(expenses);
        budget.setIncomes(incomes);
        
        Income carryOver = new Income(ZAR,Money.money(5));
        
        // Must sum all incomes and expenses before calculating carry over (Single Respons. Principle)
        // TODO: Fix failing test here
        budget.sumAllExpenses();
        budget.sumAllIncomes();
        budget.calculateCarryOver();
        assertEquals(budget.getCarryOver(),carryOver);
    }
    
    @Test
    void testIncomeRetrievalFromLabel() {
        List<Income> labelledIncomes = new ArrayList<Income>();
        
        Income salary = new Income(ZAR,Money.money(100));
        Income sideHustle = new Income(ZAR,Money.money(20));
    
        salary.setLabel("Salary");
        sideHustle.setLabel("Side Hustle");
        
        labelledIncomes.add(salary);
        labelledIncomes.add(sideHustle);
        
        Budget budget = new StandardBudget(ZAR);
        budget.setIncomes(labelledIncomes);
        
        assertEquals(salary,budget.getIncomeByLabel("Salary"));
        assertEquals(sideHustle,budget.getIncomeByLabel("Side Hustle"));
    }
    
    @Test
    void testExpenseRetrievalFromLabel() {
        List<Expense> labelledExpenses = new ArrayList<Expense>();
    
        Expense grocery = new Expense(ZAR,Money.money(100));
        Expense bill = new Expense(ZAR,Money.money(20));
    
        grocery.setLabel("Groceries");
        bill.setLabel("Bills");
    
        labelledExpenses.add(grocery);
        labelledExpenses.add(bill);
        Budget budget = new StandardBudget(ZAR);
        budget.setExpenses(labelledExpenses);
    
        assertEquals(grocery,budget.getExpenseByLabel("Groceries"));
        assertEquals(bill,budget.getExpenseByLabel("Bills"));
    }
}
