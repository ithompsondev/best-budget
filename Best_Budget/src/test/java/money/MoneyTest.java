package money;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

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
        assertEquals(new BigDecimal(20.5),walletA.getAmount());
    }
    
    @Test
    void testSubtractionOfMoneyFromTheSameCurrency() {
        Money walletA = new Money(ZAR);
        Money walletB = new Money(ZAR);
    
        walletA.setAmount(new BigDecimal(10.0));
        walletB.setAmount(new BigDecimal(10.5));
        
        walletB.subtract(walletA);
        assertEquals(new BigDecimal(0.5),walletB.getAmount());
    }
    
    @Test
    void testArbitraryValueMultiplicationWithMoney() {
        Money walletA = new Money(ZAR);
        walletA.setAmount(new BigDecimal(10));
        
        walletA.multiply(2);
        assertEquals(new BigDecimal(20.0),walletA.getAmount());
    }
    
    @Test
    void testArbitraryValueDivisionWithMoney() {
        Money walletA = new Money(ZAR);
        walletA.setAmount(new BigDecimal(10));
    
        walletA.divide(2);
        assertEquals(new BigDecimal(5.0),walletA.getAmount());
    }
    
    @Test
    void testEqualityOfMoneyOfTheSameCurrency() {
        Money walletA = new Money(ZAR);
        Money walletB = new Money(ZAR);
        
        walletA.setAmount(new BigDecimal(10));
        walletB.setAmount(new BigDecimal(10));
        
        assertEquals(walletA,walletB);
    }
}
