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

    @Test
    void addWithNStrings() {
        assertEquals(21, stringCalculator.add("1,2,3,4,5,6"));
        assertEquals(5, stringCalculator.add("5"));
        assertEquals(684, stringCalculator.add("10,42,1,1,1,1,2,4,22,110,134,142,214"));
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void addWithNewLineIncludeStrings() {
        assertEquals(3, stringCalculator.add("1\n2"));
        assertEquals(5, stringCalculator.add("5\n"));
        assertEquals(684, stringCalculator.add("10,42,1\n1,1,1,2,4,22,110\n134,142\n214"));
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void addWithCustomDelimiter() {
        assertEquals(22, stringCalculator.add("//;\n12;10"));
        assertEquals(10, stringCalculator.add("//,\n5,5"));
        assertEquals(684, stringCalculator.add("//@\n10@42@1@1@1@1@2@4@22@110@134@142@214"));
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void addWithNegativesNotAllowed(){
        try{
            stringCalculator.add("-1,4,-90");
            fail("Exception Expected for negative values");
        }catch (NumberFormatException e){
            assertEquals("negatives not allowed -1, -90", e.getMessage());
        }
    }

    @Test
    void addWithBiggerThanOneThousandShouldIgnored(){
        assertEquals(2, stringCalculator.add("1002"));
        assertEquals(222, stringCalculator.add("1222"));
        assertEquals(23, stringCalculator.add("1001,22"));
    }

    @Test
    void addWithCustomDelimiterWithAnyLength() {
        assertEquals(22, stringCalculator.add("//;;\n12;;10"));
        assertEquals(10, stringCalculator.add("//,,,\n5,,,5"));
        assertEquals(684, stringCalculator.add("//@\n10@42@1@1@1@1@2@4@22@110@134@142@214"));
        assertEquals(0, stringCalculator.add(""));
    }

}