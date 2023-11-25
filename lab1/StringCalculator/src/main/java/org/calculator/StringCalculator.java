package org.calculator;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException{

        if (numbers.isEmpty()) {
            return 0;
        }

        numbers = numbers.replace('\n', ',');
        if (numbers.contains(",,")){
            throw new IllegalArgumentException("There cannot be two delimiters in a row");
        }

        String[] numbers_array = numbers.split(",");

        int sum = 0;
        for (String number : numbers_array) {
            int num = Integer.parseInt(number);
            sum += num;
        }
        return sum;
    }
}
