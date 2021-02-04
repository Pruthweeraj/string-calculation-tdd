package com.pruthwee.stringcalculation;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class StringCalculator {


    public int add(String numbers) {

        return Arrays.stream(numbers.split(",")).filter(StringUtils::hasText)
                .mapToInt(Integer::parseInt)
                .sum();

    }
}
