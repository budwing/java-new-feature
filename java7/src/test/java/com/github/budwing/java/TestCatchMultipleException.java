package com.github.budwing.java;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCatchMultipleException {
    @Test
    public void test() {
        CatchMultipleException.catchMultiple("First");
        CatchMultipleException.catchMultiple("Second");
        Assert.assertThrows(CatchMultipleException.FirstException.class, new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                CatchMultipleException.rethrowException("First");
            }
        });
        Assert.assertThrows(CatchMultipleException.SecondException.class, new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                CatchMultipleException.rethrowException("Second");
            }
        });
    }
}
