package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class NonReifiableTypeExamples {
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
}
