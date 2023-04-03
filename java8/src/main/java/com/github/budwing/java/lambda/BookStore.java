package com.github.budwing.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class BookStore {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void printPriceCheaperThan(double price) {
        for (Book book : books) {
            if (book.getPrice() < price) {
                System.out.println(book);
            }
        }
    }

    public void printPriceBetween(double low, double high) {
        for (Book book : books) {
            if (book.getPrice() < high && book.getPrice() > low) {
                System.out.println(book);
            }
        }
    }

    public void printWithFilter(BookFilter filter) {
        for (Book book : books) {
            if (filter.test(book)) {
                System.out.println(book);
            }
        }
    }

    public void print(Predicate<Book> predicate) {
        for (Book book : books) {
            if (predicate.test(book)) {
                System.out.println(book);
            }
        }
    }

    public List<String> listBooksName(Predicate<Book> predicate) {
        List<String> booksName = new ArrayList<>();
        for (Book book : books) {
            if (predicate.test(book)) {
                booksName.add(book.getName());
            }
        }

        return booksName;
    }

    public List<String> list(Predicate<Book> predicate, Function<Book, String> function) {
        List<String> bookProps = new ArrayList<>();
        for (Book book : books) {
            if (predicate.test(book)) {
                bookProps.add(function.apply(book));
            }
        }

        return bookProps;
    }

    public void consume(Predicate<Book> predicate, Function<Book, String> function, Consumer<String> consumer) {
        for (Book book : books) {
            if (predicate.test(book)) {
                String result = function.apply(book);
                consumer.accept(result);
            }
        }
    }

}
