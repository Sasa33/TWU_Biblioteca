package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books;
    private List<Book> availableBooks = new ArrayList<Book>();
    private List<Book> checkedOutBooks = new ArrayList<Book>();

    public BookRepository(List<Book> books) {
        for (Book book : books) {
            if (book.isCheckedOut()) {
                availableBooks.add(book);
            } else {
                checkedOutBooks.add(book);
            }
        }

        this.books = books;
    }

    public List<Book> getAllBooks() {
        return this.books;
    }

    public List<Book> getAvailableBooks() {
        return availableBooks;
    }

    public List<Book> getCheckedOutBooks() {
        return checkedOutBooks;
    }
}
