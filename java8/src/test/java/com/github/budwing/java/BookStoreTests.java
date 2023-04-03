package com.github.budwing.java;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class BookStoreTests {
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
    @Test
    public void testTraditionalPrint() {
        log.info("printPriceCheaperThan(100)");
        store.printPriceCheaperThan(100);
        log.info("store.printPriceCheaperThan(50)");
        store.printPriceCheaperThan(50);
        log.info("store.printPriceBetween(50, 100)");
        store.printPriceBetween(50, 100);
    }
    @Test
    public void testPrintInnerClass() {
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
    @Test
    public void testPrintAnonymousClass() {
        store.printWithFilter(new BookFilter() {
            @Override
            public boolean test(Book book) {
                return book.getPrice()<100 && book.getPrice()>50;
            }
        });
    }
    @Test
    public void testPrintLambda() {
        log.info("store.printWithFilter(book -> book.getPrice()<100 && book.getPrice()>50)");
        store.printWithFilter(book -> book.getPrice()<100 && book.getPrice()>50);
        log.info("store.printWithFilter(book -> book.getPublishDate().isAfter(LocalDate.of(2009,10,31)))");
        store.printWithFilter(book -> book.getPublishDate().isAfter(LocalDate.of(2009,10,31)));
    }
    @Test
    public void testPrint() {
        store.print(book -> book.getPrice()<100 && book.getPrice()>50);
    }
    @Test
    public void testListBooksName() {
        List<String> names = store.listBooksName(book -> book.getPrice()<100 && book.getPrice()>50);
        for (String name: names) {
            log.info(name);
        }
    }
    @Test
    public void testList() {
        List<String> names = store.list(book -> book.getPrice()<100 && book.getPrice()>50,
                book -> book.getPublishDate().toString());
        for (String name: names) {
            log.info(name);
        }
    }
    @Test
    public void testConsume() {
        store.consume(book -> book.getPrice()<100 && book.getPrice()>50,
                book -> book.getName(),
                name -> System.out.println(name));
    }
    @Test
    public void testStream() {
        store.getBooks().stream()
                .filter(book -> book.getPrice()<100 && book.getPrice()>50)
                .map(book -> book.getName())
                .forEach(name-> System.out.println(name));
    }
}
