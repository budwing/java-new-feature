package com.github.budwing.java9;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Examples for More Concise try-with-resources Statements
 *
 * If you already have a resource as a final or effectively final variable, you can use that variable in a try-with-resources statement without declaring a new variable.
 */
public class NewFeatureExamples {
    @Test
    public void tryWithRes() {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")));
        try(br) {
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void tryWithResForJava8() {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")));
        try(BufferedReader br1 = br) {
            br1.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO: try InputStream.transferTo
     */
    @Test
    public void tryInputStreamTransferTo() {
        Assert.fail("try InputStream.transferTo");
    }

    /**
     * TODO: try ProcessHandle
     */
    @Test
    public void tryProcessHandle() {
        Assert.fail("try ProcessHandle");
    }

    /**
     * TODO: try List.of, Set.of, Map.of
     */
    @Test
    public void tryListSetMapOf() {
        Assert.fail("try List.of, Set.of, Map.of");
    }
}
