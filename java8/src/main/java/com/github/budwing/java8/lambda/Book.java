package com.github.budwing.java8.lambda;

import java.time.LocalDate;

public class Book {
    private String name;
    private double price;
    private int totalPages;
    private boolean onSale;
    private LocalDate publishDate;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public double calculateRoyalty(double price) {
        double payment = getTotalPages()*price;
        if (getTotalPages()>300) {
            payment += (getTotalPages()-300)*price*0.1;
        }

        return payment;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", totalPages=" + totalPages +
                ", onSale=" + onSale +
                ", publishDate=" + publishDate +
                '}';
    }

    public static class Builder {
        private String name;
        private Double price;
        private Integer totalPages;
        private Boolean onSale;
        private String publishDate;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder totalPages(Integer totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public Builder onSale(Boolean onSale) {
            this.onSale = onSale;
            return this;
        }

        public Builder publishDate(String publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public Book build() {
            if (name == null || name.isEmpty()) {
                throw new RuntimeException("name must be specified.");
            }
            if (price == null) {
                throw new RuntimeException("price must be specified.");
            }
            if (totalPages == null) {
                throw new RuntimeException("total pages must be specified.");
            }
            if (publishDate == null) {
                throw new RuntimeException("publish date must be specified");
            }
            Book book = new Book();
            book.name = name;
            book.price = price;
            book.totalPages = totalPages;
            book.publishDate = LocalDate.parse(publishDate);
            if (onSale==null) {
                book.onSale = true;
            } else {
                book.onSale = onSale;
            }
            return book;
        }
    }
}
