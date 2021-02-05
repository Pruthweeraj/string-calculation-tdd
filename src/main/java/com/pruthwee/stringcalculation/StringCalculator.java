package com.pruthwee.stringcalculation;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final int squeezerValue = 1000;
    private static final String defaultDelimiters = "[,\n]";

    public int add(String numbers) {

        String customDelimiter = getCustomDelimiter(numbers);

        numbers = cleanPrefixDelimiter(numbers, customDelimiter);
        List<Integer> numbersList = getNumbersFromNumberString(numbers, defaultDelimiters, customDelimiter);

        ValidateNumbers(numbersList);

        numbersList = numbersList.stream().map(bigNumber -> bigNumber % squeezerValue).collect(Collectors.toList());

        return numbersList.stream().mapToInt(i -> i).sum();

    }

    private void ValidateNumbers(List<Integer> numbersList) {
        List<Integer> negativeNumbers = numbersList.stream().filter(number -> number < 0).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(negativeNumbers)) {
            throw new NumberFormatException("negatives not allowed " + negativeNumbers.toString().substring(1, negativeNumbers.toString().length() - 1));
        }
    }

    private List<Integer> getNumbersFromNumberString(String numbers, String defaultDelimiters, String customDelimiter) {
        return Arrays.stream(numbers.split(customDelimiter != null ? customDelimiter : defaultDelimiters)).filter(StringUtils::hasText)
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }

    private String cleanPrefixDelimiter(String numbers, String customDelimiter) {
        if (null != customDelimiter) {
            numbers = removeDelimiterFormat(numbers, customDelimiter);
        }
        return numbers;
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
