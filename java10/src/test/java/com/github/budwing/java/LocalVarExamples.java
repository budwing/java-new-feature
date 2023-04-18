package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class LocalVarExamples {
    /**
     * var is identifier not keyword.
     * <p>
     * 1. Local variable declarations with initializers
     */
    @Test
    public void examplesVarIsIdentifier() {
        var list = new ArrayList<String>();
        // var list1; //illegal without initializer
        Integer var = 100;
        log.info("var is identifier not keyword ==> var list:{}, Integer var:{}", list, var);
    }

    /**
     * 2. for loop
     */
    @Test
    public void examplesForLoops() {
        // enhanced for loop
        for (var name : Arrays.asList("Tom", "John")) {
            log.info("name:{}", name);
        }
        // for index
        var sum = 0;
        for (var i = 1; i <= 100; i++) {
            sum += i;
        }
        log.info("sum result:{}", sum);
    }

    /**
     * 3. try-with-resources variable
     */
    @Test
    public void examplesForTry() {
        try (var reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")))) {
            reader.lines()
                    .map(String::length)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
