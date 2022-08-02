package com.bestbudget.io.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bestbudget.io.parser.exception.InputFormatValidationException;
import com.bestbudget.money.Expense;
import com.bestbudget.money.Income;
import com.bestbudget.money.Money;


class BudgetParserTest {
    private BudgetParser bp;
    
    // The reader will have to form input data into this standard for the parser
    // The parser will have to reformat the text
    private static final String validSingleLineInput = "income Side-Hustle 3400.50";
    private static final String[] validVector = {"income", "Side-Hustle", "3400.50"};
    private static final String invalidSingleLineInput = "incom My-New-Salary 1250";
    private static final String[] invalidVector = {"incom", "My-New-Salary", "1250"};
    
    private static String[] validMultilineInput = {
            "income my-new-salary 500",
            "expense weekly-grocery 75.34",
            "expense new-rent-expense 100"
    };
    
    private static Money[] validBudgetELements = {
            (new Income(Currency.getInstance("ZAR"), Money.money(500))
                    .andSetLabel("my-new-salary")),
            (new Expense(Currency.getInstance("ZAR"), Money.money(75.34)))
                    .andSetLabel("weekly-grocery"),
            (new Expense(Currency.getInstance("ZAR"), Money.money(100)))
                    .andSetLabel("new-rent-expense")
    };
    
    private static String[] invalidMultilineInput = {
            "income my-new-salary 500",
            "expens weekly-grocery 75.34",
            "expense new-rent-expense 100"
    };
    
    @BeforeEach
    void setUp() {
        bp = new BudgetParser(Currency.getInstance("ZAR"));
    }
    
    @Test
    void TestVectorizerForValidString() {
        String[] vector = bp.vectorize(validSingleLineInput);
        assertArrayEquals(validVector, vector);
    }
    
    @Test
    void TestVectorizerForInvalidString() {
        String[] vector = bp.vectorize(invalidSingleLineInput);
        assertArrayEquals(invalidVector, vector);
    }
    
    @Test
    void TestValidatorForValidSingleLineInput() {
        String[] vector = bp.vectorize(validSingleLineInput);
        assertEquals(true, bp.validate(vector));
    }
    
    @Test
    void TestValidatorForInvalidSingleLineInput() {
        String[] vector = bp.vectorize(invalidSingleLineInput);
        assertNotEquals(true, bp.validate(vector));
    }
    
    @Test
    void TestParserForValidSingleLineInput() throws InputFormatValidationException {
        Income expected = new Income(Currency.getInstance("ZAR"), Money.money(3400.50));
        Money actual = bp.parseLine(validSingleLineInput);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void TestParserForInvalidSingleLineInput() {
        assertThrows(InputFormatValidationException.class, () -> {
            bp.parseLine(invalidSingleLineInput);
        });
    }
    
    @Test
    void TestParserForValidMultilineInput() throws InputFormatValidationException {
        List<Money> budgetElements = bp.parseBuffer(validMultilineInput);
        
        for (int i = 0; i < validBudgetELements.length; i++) {
            assertEquals(validBudgetELements[i], budgetElements.get(i));
        }
    }
    
    @Test
    void TestParserForInvalidMultilineInput() {
        assertThrows(InputFormatValidationException.class, () -> {
            bp.parseBuffer(invalidMultilineInput);
        });
    }
}