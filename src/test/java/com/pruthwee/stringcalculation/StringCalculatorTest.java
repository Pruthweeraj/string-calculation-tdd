package com.pruthwee.stringcalculation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    @Test
    void addWithEmptyString(){
        assertEquals(0, StringCalculator.add(""));
    }

}