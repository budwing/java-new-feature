package com.github.budwing.java;

import com.github.budwing.java.lambda.Book;
import com.github.budwing.java.method.reference.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class PublisherTests {
    private List<Book> books;
    private Publisher publisher;
    @BeforeClass
    public void prepareBooks() {
        books = Arrays.asList(
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
        publisher = new Publisher();
    }
    @Test
    public void testStaticMR() {
        double pay1 = publisher.totalPayment(books, (book,price)->Publisher.generalCalculate(book, price));
        double pay2 = publisher.totalPayment(books, Publisher::generalCalculate);
        log.info("Lambda result:{}, Static method reference result:{}", pay1, pay2);
        Assert.assertEquals(pay1, pay2);
    }
    @Test
    public void testOuterInstanceMR() {
        double pay1 = publisher.totalPayment(books, (book,price)->publisher.gradeCalculate(book, price));
        double pay2 = publisher.totalPayment(books, publisher::gradeCalculate);
        log.info("Lambda result:{}, Outer instance method reference result:{}", pay1, pay2);
        Assert.assertEquals(pay1, pay2);
    }
    @Test
    public void testInstanceMR() {
        double pay1 = publisher.totalPayment(books, (book,price)->book.calculateRoyalty(price));
        double pay2 = publisher.totalPayment(books, Book::calculateRoyalty);
        log.info("Lambda result:{}, Instance method reference result:{}", pay1, pay2);
        Assert.assertEquals(pay1, pay2);
    }
    @Test
    public void testContructor() {
        List<String> booknames1 = publisher.getPublishedBooksName(books, ()->new ArrayList<>());
        List<String> booknames2 = publisher.getPublishedBooksName(books, ArrayList::new);
        log.info("Lambda result:{}, Constructor method reference result:{}", booknames1, booknames2);
    }
}
