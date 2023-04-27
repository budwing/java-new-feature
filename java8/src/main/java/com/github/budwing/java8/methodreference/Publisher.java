package com.github.budwing.java8.methodreference;

import com.github.budwing.java8.lambda.Book;

import java.util.List;
import java.util.function.Supplier;

public class Publisher {
    public static double generalCalculate(Book book, double price) {
        return book.getTotalPages()*price;
    }

    public double gradeCalculate(Book book, double price) {
        double payment = book.getTotalPages()*price;
        if (book.getTotalPages()>300) {
            payment -= (book.getTotalPages()-300)*price*0.2;
        }

        return payment;
    }

    public double totalPayment(List<Book> books, RoyaltyCalculator royaltyCalculator) {
        double totalPayment = 0;
        for (Book book:books) {
            totalPayment += royaltyCalculator.pay(book, 30);
        }

        return totalPayment;
    }

    public List<String> getPublishedBooksName(List<Book> books, Supplier<List<String>> supplier) {
        List<String> result = supplier.get();
        books.stream().map(book -> book.getName())
                .forEach(result::add);
        return result;
    }
}
