package com.github.budwing.java8;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Intermediate operations are all lazy, they can be classified into two types: Stateless and Stateful operation.
 * Execution of stateful operation on ordered stream in parallel may be very expensive.
 *
 * 1. Stateless
 *    filter()
 *    map() and mapToInt(),mapToLong(),mapToDouble()
 *    flatMap() and flatMapToInt(),flatMapToLong(),flatMapToDouble()
 *    peek()
 *
 * 2. Stateful
 *    distinct()
 *    sorted()
 *    limit()
 *    skip()
 */
@Slf4j
public class StreamIntermediateOpExamples {
    /**
     * 1.1 Stateless -> filter()
     *
     * Count the random numbers larger than 100.
     */
    @Test
    public void exampleForFilter() {
        Random random = new Random(System.currentTimeMillis());
        long count = random.ints(20, 1, 200)
                .filter(n -> n > 100)
                .peek(System.out::println)
                .count();
        log.info("count: {}", count);
    }
    /**
     * 1.2 Stateless -> map() and mapToXXX()
     *
     * The max path length within a directory.
     */
    @Test
    public void exampleForMap() throws IOException {
        OptionalInt optionalInt = Files.walk(Paths.get("F:","projects"))
                .map(Path::toString)
                .mapToInt(String::length)
                .max();
        log.info("max length: {}", optionalInt.getAsInt());
    }

    /**
     * 1.3 Stateless -> flatMap()
     *
     * collect distinct word in a file
     */
    @Test
    public void exampleForFlatMap() throws IOException {
        List<String> list = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("logback.xml")))
                .lines()
                .flatMap(line -> Arrays.stream(line.split("\\W+")))
                .filter(w->!w.isEmpty())
                .distinct()
                .collect(Collectors.toList());
        log.info("distinct word in logback.xml: {}", list);
    }
    /**
     * 1.4 Stateless -> peek()
     *
     * Stream.peek exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline
     */
    @Test
    public void exampleForPeek() {
        List<String> list = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        log.info("result: {}", list);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // following operations are stateful, parallel execution on ordered stream may be very expensive
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 2.1 Stateful -> limit()
     *
     * While limit() is generally a cheap operation on sequential stream pipelines, it can be quite expensive on ordered parallel pipelines,
     * especially for large values of maxSize, since limit(n) is constrained to return not just any n elements, but the first n elements in the encounter order.
     * Using an unordered stream source (such as generate(Supplier)) or removing the ordering constraint with BaseStream.unordered() may result in significant speedups of limit() in parallel pipelines,
     * if the semantics of your situation permit. If consistency with encounter order is required,
     * and you are experiencing poor performance or memory utilization with limit() in parallel pipelines, switching to sequential execution with BaseStream.sequential() may improve performance.
     */
    @Test
    public void exampleForLimit() {
        // Stream.iterate creates ordered stream
        Stream stream = Stream.iterate(1, t -> t + 1).parallel();//.unordered();
        long start = System.currentTimeMillis();
        // limit returns first n elements, so it's expensive for ordered parallel pipelines.
        long count = stream.limit(100000000).count();
        log.info("count:{}, time:{}ms", count, System.currentTimeMillis() - start);
    }

    /**
     * 2.2 Stateful -> skip()
     *
     * While skip() is generally a cheap operation on sequential stream pipelines, it can be quite expensive on ordered parallel pipelines,
     * especially for large values of n, since skip(n) is constrained to skip not just any n elements, but the first n elements in the encounter order.
     * Using an unordered stream source (such as generate(Supplier)) or removing the ordering constraint with BaseStream.unordered() may result in significant speedups of skip() in parallel pipelines,
     * if the semantics of your situation permit. If consistency with encounter order is required,
     * and you are experiencing poor performance or memory utilization with skip() in parallel pipelines, switching to sequential execution with BaseStream.sequential() may improve performance.
     */
    @Test
    public void exampleForSkip() {
        // Stream.iterate creates ordered stream
        Stream stream = Stream.iterate(1, t -> t + 1);//.parallel();
        long start = System.currentTimeMillis();
        // skip top n elements, it can't stop for ordered parallel pipelines.
        Optional<Integer> optional = stream.skip(100)
                .peek(System.out::println)
                .limit(1).findFirst();
        log.info("element:{}, time:{}ms", optional.get(), System.currentTimeMillis() - start);
    }

    /**
     * 2.3 Stateful -> distinct()
     *
     * Preserving stability for distinct() in parallel pipelines is relatively expensive (requires that the operation act as a full barrier, with substantial buffering overhead),
     * and stability is often not needed. Using an unordered stream source (such as generate(Supplier)) or
     * removing the ordering constraint with BaseStream.unordered() may result in significantly more efficient execution for distinct() in parallel pipelines, if the semantics of your situation permit.
     * If consistency with encounter order is required, and you are experiencing poor performance or memory utilization with distinct() in parallel pipelines,
     * switching to sequential execution with BaseStream.sequential() may improve performance.
     */
    @Test
    public void exampleForDistinct() {
        Random random = new Random(System.currentTimeMillis());
        IntStream stream = random.ints(20, 30);
        long count = stream.limit(100)
                .distinct()
                .count();
        log.info("the number of distinct elements: {}", count);
    }

    /**
     * 2.4 Stateful -> sorted()
     *
     * For ordered streams, the sort is stable. For unordered streams, no stability guarantees are made.
     */
    @Test
    public void exampleForSorted() {
        Random random = new Random(System.currentTimeMillis());
        random.ints(10,100,200)
                .peek(num->System.out.println("unsorted:"+num))
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * TODO: try parallel Stream.iterate and skip
     */
    @Test
    public void tryParallelSkip() {
        IntStream stream = IntStream.iterate(1, t->t+1);
        int first = stream.skip(100)
                .findFirst()
                .getAsInt();
        log.info("first element of an unlimited ordered stream: {}", first);
        Assert.fail("try parallel Stream.iterate and skip");
    }
}
