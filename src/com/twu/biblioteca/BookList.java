package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList(List<Book> books) {
        this.books = books;
    }


    public List<Book> listAllBooks() {
        return this.books;
    }

    public void checkoutBook(int chosen) {
        List<Book> remainingBooks = new ArrayList<Book>();

        for(int i = 0; i < books.size(); i++) {
            if(i != chosen - 1) {
                remainingBooks.add(books.get(i));
            }
        }

        this.books = remainingBooks;
    }
}
