package com.github.budwing.java8;

import com.github.budwing.java8.defaultmethod.Animal;
import com.github.budwing.java8.lambda.Book;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@Slf4j
public class DefaultMethodExamples {
    /**
     * Default method is a method with implementation in an interface.
     * It is normally used to combine functional method to facilitate the users.
     */
    @Test
    public void defaultMethodOfAnimal() {
        Animal animal1 = () -> false;
        log.info("animal1 has wings? Answer: {}", animal1.hasWings());
        animal1.fly();
        Animal animal2 = new Animal() {
            @Override
            public boolean hasWings() {
                return true;
            }

            @Override
            public void fly() {
                if (Animal.canFlyWithWings() && hasWings()) {
                    System.out.println("all animals that has wings can fly.");
                } else {
                    System.out.println("some animals can not fly even when they have wings.");
                }
            }
        };
        log.info("animal2 has wings? Answer: {}", animal2.hasWings());
        animal2.fly();
    }

    @Test
    public void functionAndThen() {
        Function<List<String>, Integer> fun = List::size;
        Function<List<String>, String> newFun = fun.andThen(size -> "The size of the list is " + size);
        log.info(newFun.apply(Arrays.asList("a", "b")));
    }

    @Test
    public void functionCompose() {
        Function<List<String>, Integer> fun = List::size;
        Function<Object, Integer> newFun = fun.compose(o -> Arrays.asList(o.toString()));
        log.info("result:{}", newFun.apply("test"));
    }

    @Test
    public void predicateAnd() {
        Predicate<List<String>> predicate = list -> list.size() > 1;
        predicate = predicate.and(list -> list.get(0).isEmpty())
                .or(list -> list.size() == 1);
        log.info("result:{}", predicate.test(Arrays.asList("x", "")));
    }

    @Test
    public void consumerAndThen() {
        Consumer<List> consumer = System.out::println;
        consumer = consumer.andThen(list -> System.out.println(list.size()));
        consumer.accept(Arrays.asList("a", "b"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * TODO: try Function.andThen, first: Book -> name, second: name -> length
     */
    @Test
    public void bookNameLength() {
        List<Book> books = Arrays.asList(
                new Book.Builder().name("Book A").price(45.2).publishDate("1993-01-09").totalPages(235).build(),
                new Book.Builder().name("Book B").price(23.0).publishDate("2008-03-25").totalPages(321).build(),
                new Book.Builder().name("Book C").price(57.8).publishDate("2018-07-02").totalPages(345).build(),
                new Book.Builder().name("Book D").price(13.5).publishDate("2012-11-28").totalPages(122).build(),
                new Book.Builder().name("Book E").price(29.6).publishDate("2002-03-30").totalPages(201).build(),
                new Book.Builder().name("Book F").price(79.8).publishDate("2022-12-21").totalPages(458).build(),
                new Book.Builder().name("Book G").price(54.7).publishDate("2016-09-12").totalPages(339).build(),
                new Book.Builder().name("Book H").price(69.6).publishDate("2007-03-22").totalPages(533).build(),
                new Book.Builder().name("Book I").price(09.9).publishDate("1995-10-28").totalPages(145).build(),
                new Book.Builder().name("Book J").price(120.0).publishDate("2022-08-09").totalPages(398).build()
        );
        Assert.fail("try Function.andThen, first: Book -> name, second: name -> length");
    }

    /**
     * TODO: try Predicate.or, first: price>100, second: page>500
     */
    @Test
    public void bookPriceOrPage() {
        List<Book> books = Arrays.asList(
                new Book.Builder().name("Book A").price(45.2).publishDate("1993-01-09").totalPages(235).build(),
                new Book.Builder().name("Book B").price(23.0).publishDate("2008-03-25").totalPages(321).build(),
                new Book.Builder().name("Book C").price(57.8).publishDate("2018-07-02").totalPages(345).build(),
                new Book.Builder().name("Book D").price(13.5).publishDate("2012-11-28").totalPages(122).build(),
                new Book.Builder().name("Book E").price(29.6).publishDate("2002-03-30").totalPages(201).build(),
                new Book.Builder().name("Book F").price(79.8).publishDate("2022-12-21").totalPages(458).build(),
                new Book.Builder().name("Book G").price(54.7).publishDate("2016-09-12").totalPages(339).build(),
                new Book.Builder().name("Book H").price(69.6).publishDate("2007-03-22").totalPages(533).build(),
                new Book.Builder().name("Book I").price(09.9).publishDate("1995-10-28").totalPages(145).build(),
                new Book.Builder().name("Book J").price(120.0).publishDate("2022-08-09").totalPages(398).build()
        );
        Assert.fail("try Predicate.or, first: price>100, second: page>500");
    }
}
