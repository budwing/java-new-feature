package com.github.budwing.java;

import org.testng.annotations.Test;

public class TestUnderscoreNumericLiteral {
    @Test
    public void testLegalUsage() {
        UnderscoreNumericLiteral.legalUsage();
    }
    @Test
    public void testIllegalUsage() {
        UnderscoreNumericLiteral.illegalUsage();
    }
}
