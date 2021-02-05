package com.pruthwee.stringcalculation;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    private static final int squeezerValue = 1000;
    private static final String defaultDelimiters = "[,\n]";
    private static final String customDelimiterRegex = "\\[(.*?)\\]";

    public int add(String numbers) {

        String customDelimiter = getCustomDelimiter(numbers);
        numbers = cleanPrefixDelimiter(numbers, customDelimiter);
        List<Integer> numbersList = getNumbersFromNumberString(numbers, customDelimiter);
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

    private List<Integer> getNumbersFromNumberString(String numbers, String customDelimiter) {
        return Arrays.stream(numbers.split(customDelimiter != null ? customDelimiter : StringCalculator.defaultDelimiters)).filter(StringUtils::hasText)
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }

    private String cleanPrefixDelimiter(String numbers, String customDelimiter) {
        if (null != customDelimiter) {
            numbers = removeDelimiterFormat(numbers);
        }
        return numbers;
    }

    private String removeDelimiterFormat(String numbers) {
        String delimiterFormat = numbers.substring(0, numbers.indexOf("\n") + 1);
        return numbers.replace(delimiterFormat, "");
    }

    private String getCustomDelimiter(String numbers) {

        if (numbers.startsWith("//[")) {
            return customDelimiterWithMultipleToken(numbers);
        } else if (numbers.startsWith("//")) {
            return customDelimiterWithSingleToken(numbers);
        }
        return null;
    }

    private String customDelimiterWithMultipleToken(String numbers) {
        int delimiterEndIndex = numbers.indexOf("\n");
        String delimiterString = numbers.substring(2, delimiterEndIndex);

        List<String> allDelimiters = getAllDelimiters(delimiterString);

        return formatMultipleTokenRegex(allDelimiters);
    }

    private List<String> getAllDelimiters(String delimiterString) {
        final Pattern pattern = Pattern.compile(customDelimiterRegex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(delimiterString);

        List<String> multiRegexStrings = new ArrayList<>();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String regVal = matcher.group(i);
                if (regVal != null) {
                    multiRegexStrings.add(regVal);
                }
            }
        }
        return multiRegexStrings;
    }

    private String formatMultipleTokenRegex(List<String> allDelimiters) {
        return "[" + String.join("", allDelimiters) + "]";
    }

    private String customDelimiterWithSingleToken(String numbers) {
        int delimiterEndIndex = numbers.indexOf("\n");
        return numbers.substring(2, delimiterEndIndex);
    }

}
