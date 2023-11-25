package org.calculator;

public class StringCalculator {
    public int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }

        String[] numbers_array = numbers.split(",");

        if (numbers_array.length > 2) {
            throw new IllegalArgumentException("Only two numbers are allowed.");
        }

        int sum = 0;
        for (String number : numbers_array) {
            int num = Integer.parseInt(number);
            sum += num;
        }
        return sum;
    }
}
