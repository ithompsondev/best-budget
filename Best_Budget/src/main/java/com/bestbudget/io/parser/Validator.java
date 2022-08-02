package com.bestbudget.io.parser;

import com.bestbudget.io.parser.exception.InputFormatValidationException;


public abstract class Validator<T> {
    /**
     * A parser must define a method in which to decompose a single line of input into
     * a vector (buffer) of input.
     *
     * @param input A single line of input
     * @return A buffer from the decomposition of a single line of input
     */
    protected abstract T[] vectorize(T input);
    
    /**
     * A parser must define a method in which the elements of any given vector (buffer) must
     * be validated to ensure that the input data conforms to the format specified by the application
     *
     * @param vector The input vector (buffer)
     * @return The result of validating each element in the vector
     * @throws InputFormatValidationException When the input vector does not conform to the format
     * described by the application
     */
    protected abstract boolean validate(T[] vector) throws InputFormatValidationException;
}