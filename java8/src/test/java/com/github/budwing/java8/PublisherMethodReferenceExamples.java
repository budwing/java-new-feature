package com.github.budwing.java8;

import com.github.budwing.java8.lambda.Book;
import com.github.budwing.java8.methodreference.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class PublisherMethodReferenceExamples {
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

    /**
     * TODO: change to static MR
     */
    @Test
    public void staticMR() {
        double pay1 = publisher.totalPayment(books, (book,price)->Publisher.generalCalculate(book, price));
        double pay2 = 0; //
        log.info("Lambda result:{}, Static method reference result:{}", pay1, pay2);
        Assert.assertEquals(pay1, pay2);
    }

    /**
     * TODO: change to global instance MR
     */
    @Test
    public void globalInstanceMR() {
        double pay1 = publisher.totalPayment(books, (book,price)->publisher.gradeCalculate(book, price));
        double pay2 = 0;
        log.info("Lambda result:{}, Outer instance method reference result:{}", pay1, pay2);
        Assert.assertEquals(pay1, pay2);
    }

    /**
     * TODO: change to instance MR
     */
    @Test
    public void instanceMR() {
        double pay1 = publisher.totalPayment(books, (book,price)->book.calculateRoyalty(price));
        double pay2 = 0;
        log.info("Lambda result:{}, Instance method reference result:{}", pay1, pay2);
        Assert.assertEquals(pay1, pay2);
    }

    /**
     * TODO: change to constructor MR
     */
    @Test
    public void constructorMR() {
        List<String> booknames1 = publisher.getPublishedBooksName(books, ()->new ArrayList<>());
        List<String> booknames2 = new ArrayList<>();
        log.info("Lambda result:{}, Constructor method reference result:{}", booknames1, booknames2);
        Assert.assertEquals(booknames1.size(), booknames2.size());
    }
}
