package com.pruthwee.stringcalculation;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class StringCalculator {


    public int add(String numbers) {
        if (!StringUtils.hasText(numbers)) {
            return 0;
        }

        String[] numberArray = numbers.split(",");
        return Arrays.stream(numberArray).mapToInt(Integer::parseInt).sum();

    }
}
