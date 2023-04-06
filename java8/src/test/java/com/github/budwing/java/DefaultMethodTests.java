package com.github.budwing.java;

import com.github.budwing.java.defaultmethod.Bird;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class DefaultMethodTests {
    @Test
    public void testBird() {
        Bird bird1 = ()-> System.out.println("I can fly.");
        bird1.fly();
        bird1.run();
        Bird bird2 = new Bird() {
            @Override
            public void fly() {
                System.out.println("I can't fly.");
            }

            @Override
            public void run() {
                System.out.println("I can't run.");
            }
        };
        bird2.fly();
        bird2.run();
    }
    @Test
    public void testAndThen() {
        Function<List<String>, Integer> fun = List::size;
        Function<List<String>, String> newFun = fun.andThen(size->"The size of the list is "+size);
        log.info(newFun.apply(Arrays.asList("a","b")));
    }
    @Test
    public void testCompose() {
        Function<List<String>, Integer> fun = List::size;
        Function<Object, Integer> newFun = fun.compose(o -> Arrays.asList(o.toString()));
        log.info("result:{}",newFun.apply("test"));
    }
    @Test
    public void testPredicate() {
        Predicate<List<String>> predicate = list->list.size()>1;
        predicate = predicate.and(list->list.get(0).isEmpty())
                .or(list->list.size()==1);
        log.info("result:{}",predicate.test(Arrays.asList("x","")));
    }
    @Test
    public void testConsumer() {
        Consumer<List> consumer = System.out::println;
        consumer = consumer.andThen(list -> System.out.println(list.size()));
        consumer.accept(Arrays.asList("a", "b"));
    }
}
