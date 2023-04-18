package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class TestNonReifiableType {
    @Test
    public void testHeapPollutionClassCast() {
        Assert.assertThrows(ClassCastException.class, new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                try {
                    NonReifiableType.heapPollutionClassCast();
                } catch (Exception e) {
                    log.info("got exception {}", e.getClass());
                    throw e;
                }
            }
        });
    }

    @Test
    public void testHeapPollution() {
        NonReifiableType.heapPollutionUncheckedWarning();
    }

    @Test
    public void testArrayBuilder() {
        List<String> stringListA = new ArrayList<String>();
        List<String> stringListB = new ArrayList<String>();

        NonReifiableType.addToListUncheckedWarning(stringListA, "Seven", "Eight", "Nine");
        NonReifiableType.addToListUncheckedWarning(stringListA, "Ten", "Eleven", "Twelve");
        List<List<String>> listOfStringLists = new ArrayList<List<String>>();
        NonReifiableType.addToListUncheckedWarning(listOfStringLists, stringListA, stringListB);
    }

    @Test
    public void testVarargsCass() {
        Assert.assertThrows(ClassCastException.class, new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                NonReifiableType.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
            }
        });
    }

    /**
     * Diamond operator can't be used with anonymous inner class
     */
    @Test
    public void exampleDiamondWithInnerClass() throws Exception {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "it's just a test for diamond operator with anonymous inner class.";
            }
        };
        log.info(c.call());
    }
}
