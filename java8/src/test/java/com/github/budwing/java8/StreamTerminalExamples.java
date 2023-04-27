package com.github.budwing.java8;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. findFirst/findAny
 * 2. anyMatch/allMatch/noneMatch
 * 3. min/max/count
 * 4. reduce
 * 5. collect
 * 6. forEach/forEachOrdered
 * 7. toArray

 */
@Slf4j
public class StreamTerminalExamples {
    /**
     * 1. findFirst/findAny
     * <p>
     * They are all short-circuiting, can be used on infinite stream
     */
    @Test
    public void exampleForFind() {
        Optional<Long> optional = Stream.generate(System::currentTimeMillis)
                .findFirst();
        log.info("findFirst: {}", optional.get());
    }

    /**
     * 2. anyMatch/allMatch/noneMatch
     *
     * They are short-circuiting.
     */
    @Test
    public void exampleForMatch() {
        Random random = new Random(System.currentTimeMillis());
        boolean r = random.ints(100, 200)
                .allMatch(num -> num > 200);
        log.info("match result:{}", r);
    }
    /**
     * 3. min/max/count
     */
    @Test
    public void exampleForMinMaxCount() {
        Random random = new Random(System.currentTimeMillis());
        OptionalInt optionalInt = random.ints(10,100, 200)
                .min();
        log.info("min result:{}", optionalInt.getAsInt());
        optionalInt = random.ints(10,100, 200)
                .max();
        log.info("max result:{}", optionalInt.getAsInt());
        long count = random.ints(10,100, 200)
                .count();
        log.info("count result:{}", count);
    }

    /**
     * reduce is used to accumulate stream
     */
    @Test
    public void exampleForReduce() {
        int result = IntStream.rangeClosed(1, 1000)
                .reduce((a, b) -> a + b)
                .getAsInt();
        log.info("result:{}", result);
        // identity here is an initial value of the accumulation
        result = IntStream.rangeClosed(1, 1000)
                .reduce(1, (a, b) -> a + b);
        log.info("result with identity 10: {}", result);
        // identity will be accumulated multiply times in parallel mode
        result = IntStream.rangeClosed(1, 1000)
                .parallel()
                .reduce(1, (a, b) -> a + b);
        log.info("parallel result with identity 10: {}", result);
        // combiner is used to reduce a different type
        result = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")))
                .lines()
                .reduce(0, (l, b) -> l > b.length() ? l : b.length(), Integer::max);
        log.info("max length:{}", result);
    }

    /**
     * collect is used to collect elements to a container
     */
    @Test
    public void exampleForCollect() {
        List<String> words = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")))
                .lines()
                .flatMap(line -> Arrays.stream(line.split("\\W+")))
                .distinct()
                .filter(word -> !word.isEmpty())
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        log.info("collect result: {}", words);
    }

    /**
     * Collectors.groupBy is used to collect elements to a map
     */
    @Test
    public void exampleForGroupByLength() {
        Map words = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")))
                .lines()
                .flatMap(line -> Arrays.stream(line.split("\\W+")))
                .distinct()
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(String::length, Collectors.joining("-")));
        log.info("collect result: {}", words);
    }

}
