package com.github.budwing.java;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Examples for More Concise try-with-resources Statements
 *
 * If you already have a resource as a final or effectively final variable, you can use that variable in a try-with-resources statement without declaring a new variable.
 */
public class TryWithResExamples {
    @Test
    public void exampleForEnhanced() {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")));
        try(br) {
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void exampleForJava8() {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")));
        try(BufferedReader br1 = br) {
            br1.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
