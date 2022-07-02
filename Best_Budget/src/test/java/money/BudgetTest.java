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
            new Income(ZAR,new BigDecimal(10.0)),
            new Income(ZAR,new BigDecimal(20.01)),
            new Income(ZAR,new BigDecimal(30.02)),
            new Income(ZAR,new BigDecimal(40.03)),
            new Income(ZAR,new BigDecimal(50.05))
    });
    private static final List<Expense> expenses = Arrays.asList(new Expense[] {
            new Expense(ZAR,new BigDecimal(9.0)),
            new Expense(ZAR,new BigDecimal(19.01)),
            new Expense(ZAR,new BigDecimal(29.02)),
            new Expense(ZAR,new BigDecimal(39.03)),
            new Expense(ZAR,new BigDecimal(49.05))
    });
    
    @Test
    void testAddAllIncomesOfTheSameCurrency() {
        Budget budget = new StandardBudget(ZAR);
        budget.setIncomes(incomes);
        
        Money expectedSum = new Money(ZAR);
        expectedSum.setAmount(new BigDecimal(150.11));
        
        budget.sumAllIncomes();
        assertEquals(expectedSum,budget.getTotalIncome());
    }
    
    @Test
    void testAddAllExpensesOfTheSameCurrency() {
        Budget budget = new StandardBudget(ZAR);
        budget.setExpenses(expenses);
    
        Money expectedSum = new Money(ZAR);
        expectedSum.setAmount(new BigDecimal(145.11));
    
        budget.sumAllExpenses();
        assertEquals(expectedSum,budget.getTotalExpense());
    }
    
    @Test
    void testCorrectCarryOverCalculationFromSameCurrencyIncomesAndExpenses() {
        Budget budget = new StandardBudget(ZAR);
        budget.setExpenses(expenses);
        budget.setIncomes(incomes);
        
        Income carryOver = new Income(ZAR,new BigDecimal(0.5));
        
        budget.calculateCarryOver();
        assertEquals(budget.getCarryOver(),carryOver);
    }
}
