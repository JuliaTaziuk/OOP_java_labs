package org.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StringCalculatorTest {
    StringCalculator calculator = new StringCalculator();

    @Test
    void EmptyStringsSumIsZero() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    void NumberIsEqualToItself() {
        assertEquals(1, calculator.add("1"));
    }

    @Test
    void SumOfTwoComaSeparatedNumbers() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    void SumOfMoreThanTwoNumbersIsNotAllowed() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.add("1,2,3"));
    }
}
