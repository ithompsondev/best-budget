package money;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

import com.bestbudget.money.Expense;
import com.bestbudget.money.Income;
import com.bestbudget.money.Money;


public class MoneyTest {
    private static final String ZAR_TEST_CURRENCY = "ZAR";
    private static final Currency ZAR = Currency.getInstance(ZAR_TEST_CURRENCY);
    
    @Test
    void testAdditionOfMoneyFromTheSameCurrency() {
        Money walletA = new Money(ZAR);
        Money walletB = new Money(ZAR);
        
        walletA.setAmount(new BigDecimal(10.0));
        walletB.setAmount(new BigDecimal(10.5));
        
        walletA.add(walletB);
        assertEquals(new Money(ZAR,new BigDecimal(20.5)),walletA);
    }
    
    @Test
    void testSubtractionOfMoneyFromTheSameCurrency() {
        Money walletA = new Money(ZAR);
        Money walletB = new Money(ZAR);
    
        walletA.setAmount(new BigDecimal(10.0));
        walletB.setAmount(new BigDecimal(10.5));
        
        walletB.subtract(walletA);
        assertEquals(new Money(ZAR,new BigDecimal(0.5)),walletB);
    }
    
    @Test
    void testArbitraryValueMultiplicationWithMoney() {
        Money walletA = new Money(ZAR);
        walletA.setAmount(new BigDecimal(10));
        
        walletA.multiply(2);
        assertEquals(new Money(ZAR,new BigDecimal(20)),walletA);
    }
    
    @Test
    void testArbitraryValueDivisionWithMoney() {
        Money walletA = new Money(ZAR);
        walletA.setAmount(new BigDecimal(10));
    
        walletA.divide(2);
        assertEquals(new Money(ZAR,new BigDecimal(5)),walletA);
    }
    
    @Test
    void testEqualityOfMoneyOfTheSameCurrency() {
        Money walletA = new Money(ZAR);
        Money walletB = new Money(ZAR);
        
        walletA.setAmount(new BigDecimal(10));
        walletB.setAmount(new BigDecimal(10));
        
        assertEquals(walletA,walletB);
    }
    
    @Test
    void testEqualityOfIncomeOfTheSameCurrency() {
        Income walletA = new Income(ZAR);
        Income walletB = new Income(ZAR);
        
        walletA.setAmount(new BigDecimal(10));
        walletB.setAmount(new BigDecimal(10));
        
        assertEquals(walletA,walletB);
    }
    
    @Test
    void testEqualityOfExpenseOfTheSameCurrency() {
        Expense walletA = new Expense(ZAR);
        Expense walletB = new Expense(ZAR);
        
        walletA.setAmount(new BigDecimal(10));
        walletB.setAmount(new BigDecimal(10));
        
        assertEquals(walletA,walletB);
    }
}
