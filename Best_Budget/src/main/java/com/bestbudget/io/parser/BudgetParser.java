package com.bestbudget.io.parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import com.bestbudget.io.parser.exception.InputFormatValidationException;
import com.bestbudget.money.Expense;
import com.bestbudget.money.Income;
import com.bestbudget.money.Money;


/**
 * Concretely defined methods for parsing budget related information from a single line of input
 * or a collection of inputs.
 */
public class BudgetParser extends Validator<String> implements Parser<Money> {
    private static final int TYPE = 0;
    private static final int LABEL = 1;
    private static final int AMOUNT = 2;
    private static final int SIZE = 3;
    private String[] vector;
    private Currency currency;
    private Class currentType;
    private String currentLabel;
    private double currentAmount;
    
    public BudgetParser(Currency currency) {
        this.currency = currency;
    }
    
    // Vectorization must always occur before parsing begins.
    @Override
    protected String[] vectorize(String line) {
        vector = line.split("\\s");
        return vector;
    }
    
    // We set current values in one pass for a successful validation
    @Override
    protected boolean validate(String[] vector) {
        return (
            validateInputVectorLength()
            && validateInputType()
            && validateInputLabel()
            && validateInputAmount()
        );
    }
    
    @Override
    public Money parseLine(String line) throws InputFormatValidationException {
        vectorize(line);
        if (!validate(vector)) {
            throw new InputFormatValidationException("Invalid budget information input");
        }
        
        return (currentType == Income.class)
                ? new Income(currency, Money.money(currentAmount)).andSetLabel(currentLabel)
                : new Expense(currency, Money.money(currentAmount)).andSetLabel(currentLabel);
    }
    
    @Override
    public List<Money> parseBuffer(String[] buffer) throws InputFormatValidationException {
        List<Money> budgetElements = new ArrayList<>();
        for (String element: buffer) {
            budgetElements.add(parseLine(element));
        }
        
        return budgetElements;
    }
    
    // Validation utility methods
    private boolean validateInputVectorLength() {
        return vector.length == SIZE;
    }
    
    private boolean validateInputType() {
        if (!(vector[TYPE].equalsIgnoreCase("income")
                || vector[TYPE].equalsIgnoreCase("expense"))) {
            return false;
        } else {
            currentType = vector[TYPE].equalsIgnoreCase("income") ? Income.class: Expense.class;
            return true;
        }
    }
    
    private boolean validateInputLabel() {
        if (vector[LABEL].isEmpty()) {
            return false;
        } else {
            // Reformat label into readable text
            createReadableLabel(vector[LABEL]);
            return true;
        }
    }
    
    private boolean validateInputAmount() {
        try {
            currentAmount = Double.parseDouble(vector[AMOUNT]);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }
    
    private void createReadableLabel(String label) {
        currentLabel = String.join(
                " ",
                Arrays.stream(label.split("-")).map(
                    word -> {
                        word = word.toLowerCase();
                        return word.substring(0,1).toUpperCase() + word.substring(1);
                    }
                ).collect(Collectors.toList()).toArray(new String[0])
        );
    }
}
