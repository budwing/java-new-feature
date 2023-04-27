package com.github.budwing.java8;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

@Slf4j
public class SpliteratorExamples {
    @Test
    public void spliteratorForEach() {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h");
        Spliterator spliterator = list.spliterator();
        spliterator.forEachRemaining(System.out::println);
    }
    @Test
    public void spliteratorTryAdvance() {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h");
        Spliterator spliterator = list.spliterator();
        while (spliterator.tryAdvance(System.out::println));
    }

    /**
     * TODO: try to split list
     */
    @Test
    public void trySplitList() {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h");
        Assert.fail("try to split list");
    }
}
