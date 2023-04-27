package com.github.budwing.java8;

import com.github.budwing.java8.lambda.Book;
import com.github.budwing.java8.lambda.BookFilter;
import com.github.budwing.java8.lambda.BookStore;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
public class BookStoreLambdaExamples {
    private BookStore store;
    @BeforeClass
    public void prepareBooks() {
        store = new BookStore();
        store.setBooks(
                Arrays.asList(
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
                )
        );
    }

    /**
     * traditional OOAD requires new methods when there are new requirements.
     * You will have to add a method even when nothing is changed except for condition.
     */
    @Test
    public void traditionalPrint() {
        log.info("printPriceCheaperThan(100)");
        store.printPriceCheaperThan(100);
        log.info("store.printPriceCheaperThan(50)");
        store.printPriceCheaperThan(50);
        log.info("store.printPriceBetween(50, 100)");
        store.printPriceBetween(50, 100);
    }

    /**
     * Adding a new abstraction to represent the conditions. It's a pattern named protected variation.
     * But it looks a little complex.
     */
    @Test
    public void printWithInnerClass() {
        class BookPriceFiler implements BookFilter {
            private double low;
            private double high;

            public BookPriceFiler(double low, double high) {
                this.low = low;
                this.high = high;
            }

            @Override
            public boolean test(Book book) {
                return book.getPrice()<high && book.getPrice()>low;
            }
        }
        store.printWithFilter(new BookPriceFiler(50,100));
    }

    /**
     * With anonymous inner class, it looks better.
     */
    @Test
    public void printWithAnonymousInnerClass() {
        store.printWithFilter(new BookFilter() {
            @Override
            public boolean test(Book book) {
                return book.getPrice()<100 && book.getPrice()>50;
            }
        });
    }

    /**
     * Lambda makes thing clearer.
     */
    @Test
    public void printWithLambda() {
        log.info("store.printWithFilter(book -> book.getPrice()<100 && book.getPrice()>50)");
        store.printWithFilter(book -> book.getPrice()<100 && book.getPrice()>50);
        log.info("store.printWithFilter(book -> book.getPublishDate().isAfter(LocalDate.of(2009,10,31)))");
        store.printWithFilter(book -> book.getPublishDate().isAfter(LocalDate.of(2009,10,31)));
    }

    /**
     * Java 8 defines several standard functional interfaces that can support most of the cases.
     */
    @Test
    public void printWithPredicate() {
        store.print(book -> book.getPrice()<100 && book.getPrice()>50);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// try yourselves
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * TODO: check BookStore.listBooksName method, select the names of books which its price is between 50 and 100.
     */
    @Test
    public void tryListBooksName() {
        Assert.fail("check BookStore.listBooksName method, select the names of books which its price is between 50 and 100.");
    }

    /**
     * TODO: check BookStore.list method, select the published date of books which its price is between 50 and 100.
     */
    @Test
    public void tryList() {
        Assert.fail("check BookStore.list method, select the published date of books which its price is between 50 and 100.");
    }

    /**
     * TODO: check BookStore.consume method, print all the book names which its price is between 50 and 100.
     */
    @Test
    public void tryConsume() {
        Assert.fail("check BookStore.consume method, print all the book names which its price is between 50 and 100.");
    }

    /**
     * Java 8 Stream makes thing more general.
     * TODO: If we don't have BookStore.consume method, can we print book names by Lambda?
     */
    @Test
    public void tryStream() {
        Assert.fail("If we don't have BookStore.consume method, can we print book names by Lambda?");
    }
}
