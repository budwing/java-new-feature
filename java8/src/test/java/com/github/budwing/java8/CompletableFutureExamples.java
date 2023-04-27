package com.github.budwing.java8;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture is both Future and CompletableStage.
 */
@Slf4j
public class CompletableFutureExamples {
    /**
     * CompletableFuture.supplyAsync accepts a supplier and returns a CompletableFuture which may generate a value.
     */
    @Test
    public void exampleForSupplyAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                // this simulates the time to get data
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "random string content";
        });
        CompletableFuture<Void> newStage = completableFuture.thenApply(String::length).thenAccept(System.out::println);
        log.info("you should be able to see this first.");
        // you can do something else at the same time
        // Thread.sleep(1000);
        log.info("invoke join to block main thread");
        newStage.join();
        log.info("exit");
    }

    /**
     * CompletableFuture.runAsync accepts Runnable, it generates CompletableFuture<Void>
     */
    @Test
    public void exampleForRunAsync() throws InterruptedException {
        Path testFilePath = Paths.get(System.getProperty("user.home"), "test.file.txt");
        // prepare a test file for reading
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                Files.write(testFilePath, "a test file".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        CompletableFuture<Void> newStage = completableFuture.thenRun(() -> {
            try {
                long words = Files.readAllLines(testFilePath, Charset.defaultCharset())
                        .stream()
                        .flatMap(s -> Arrays.stream(s.split("\\W+")))
                        .count();
                log.info("the number of the words {}", words);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        log.info("call get to block the main thread.");
        // the difference between get and join is that, get throws no runtime exception
        try {
            newStage.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        newStage.thenRun(() -> {
            try {
                Files.delete(testFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Stage triggered after two stages’ completion
     * thenAcceptBoth --> BiConsumer
     * thenCombine    --> BiFunction
     * runAfterBoth   --> Runnable
     */
    @Test
    public void exampleForBoth() {
        Random random = new Random(System.currentTimeMillis());

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(random::nextInt)
                .thenCombine(CompletableFuture.supplyAsync(random::nextInt), (a, b) -> a + b);
        log.info("the result: {}", completableFuture.join());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * TODO: search key words "java new feature" from both baidu and bing, return the content of the first response
     */
    @Test
    public void trySearchFromBaiduOrBing() {
        Assert.fail("search key words \"java new feature\" from both baidu and bing, return the content of the first response");
    }
}
