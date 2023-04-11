package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

@Slf4j
public class SpliteratorExamples {
    @Test
    public void testSpliteratorForEach() {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h");
        Spliterator spliterator = list.spliterator();
        spliterator.forEachRemaining(System.out::println);
    }
    @Test
    public void testSpliteratorTryAdvance() {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h");
        Spliterator spliterator = list.spliterator();
        while (spliterator.tryAdvance(System.out::println));
    }
    @Test
    public void testSpliterator() {
        List<String> list = Arrays.asList("a","b","c","d","e","f","g","h");
        Spliterator s1 = list.spliterator();
        Spliterator s2 = s1.trySplit();
        Spliterator s3 = s2.trySplit();
        s1.forEachRemaining(System.out::println);
        s2.forEachRemaining(System.out::println);
        s3.forEachRemaining(System.out::println);
    }
}
