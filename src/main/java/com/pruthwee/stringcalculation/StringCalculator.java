package com.pruthwee.stringcalculation;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class StringCalculator {


    public int add(String numbers) {
        String defaultDelimiters = "[,\n]";
        String customDelimiter = getCustomDelimiter(numbers);
        if (null != customDelimiter) {
            numbers = removeDelimiterFormat(numbers, customDelimiter);
        }

        return Arrays.stream(numbers.split(customDelimiter != null ? customDelimiter : defaultDelimiters)).filter(StringUtils::hasText)
                .mapToInt(Integer::parseInt)
                .sum();

    }

    private String removeDelimiterFormat(String numbers, String customDelimiter) {
        String delimiterFormat = "//" + customDelimiter + "\n";
        return numbers.replace(delimiterFormat, "");
    }

    private String getCustomDelimiter(String numbers) {

        if (numbers.startsWith("//")) {
            return String.valueOf(numbers.charAt(2));
        }
        return null;
    }
}
