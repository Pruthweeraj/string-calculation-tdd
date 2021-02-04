package com.pruthwee.stringcalculation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private static StringCalculator stringCalculator;

    @BeforeAll
    static void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    void addWithEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void addWithOneString() {
        assertEquals(3, stringCalculator.add("3"));
        assertEquals(5, stringCalculator.add("5"));
        assertEquals(10, stringCalculator.add("10"));
    }

    @Test
    void addWithTwoString() {
        assertEquals(3, stringCalculator.add("1,2"));
        assertEquals(10, stringCalculator.add("5,5"));
        assertEquals(52, stringCalculator.add("10,42"));
    }

}