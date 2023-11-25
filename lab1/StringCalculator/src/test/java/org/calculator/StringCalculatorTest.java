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
    void SumOfTenComaSeparatedNumbers() {
        assertEquals(1034, calculator.add("4,22,641,13,8,19,0,54,272,1"));
    }

    @Test
    void SumOfThreeNumbersSeparatedByComaAndNewLineCharacter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void TwoDelimitersInRowShouldReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.add("1,\n2"));
    }

    @Test
    void SumOfTheStringWithCustomDelimiters() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void SumOfTheStringWithCustomDelimiters2() {
        assertEquals(60, calculator.add("//-\n22-5\n21,1\n2-9"));
    }

    @Test
    void TwoCustomDelimitersInRowShouldReturnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.add("//;\n1;;;1;1"));
    }

    @Test
    void SumOfTheStringWithNegativeNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.add("-2,-1,-4"));
    }

    @Test
    void NumbersAboveThousandDoNotCount() {
        assertEquals(1295, calculator.add("82,194\n1000,19\n2824"));
    }

    @Test
    void UnknownDelimiterThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.add("\"//[**]\\n1**2*3\""));
    }
    @Test
    void SumOfTheStringWithCustomDelimitersOfAnyLenght() {
        assertEquals(6, calculator.add("//[**]\n1**2,3"));
    }
}
