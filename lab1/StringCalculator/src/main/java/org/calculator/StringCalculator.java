package org.calculator;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException{

        if (numbers.isEmpty()) {
            return 0;
        }

        numbers = remove_delimiters(numbers);
        numbers = numbers.replace('\n', ',');

        if (numbers.contains(",,")){
            throw new IllegalArgumentException("There cannot be two delimiters in a row");
        }

        String[] numbers_array = numbers.split(",");

        check_for_negative_numbers(numbers_array);

        int sum = 0;
        for (String number : numbers_array) {
            int num = Integer.parseInt(number);

            if (num <= 1000){
                sum += num;
            }
        }

        return sum;
    }

    private String remove_delimiters(String numbers){
        if (numbers.startsWith("//")) {
            int newline_index = numbers.indexOf("\n");
            if (newline_index != -1) {
                String delimiters = numbers.substring(2, newline_index);
                numbers = numbers.substring(newline_index + 1);
                return numbers.replaceAll("[" + Pattern.quote(delimiters) + "]*", ",");
            }
        }
        return numbers;
    }

    private void check_for_negative_numbers(String[] numbers) {
        List<Integer> negative_numbers = new ArrayList<>();

        for (String number : numbers) {
            int num = Integer.parseInt(number);

            if (num < 0) {
                negative_numbers.add(num);
            }
        }

        if (!negative_numbers.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers are not supported: " + negative_numbers);
        }
    }
}

