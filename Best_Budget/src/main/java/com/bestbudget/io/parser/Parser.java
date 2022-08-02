package com.bestbudget.io.parser;

import java.util.List;

import com.bestbudget.io.parser.exception.InputFormatValidationException;


public interface Parser<T> {
    public T parseLine(String line) throws InputFormatValidationException;
    public List<T> parseBuffer(String[] buffer) throws InputFormatValidationException;
}
