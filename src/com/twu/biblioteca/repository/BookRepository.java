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
                checkedOutBooks.add(book);
            } else {
                availableBooks.add(book);
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

    public List<Book> findBooksByOwnerNumber(String userNumber) {
        List<Book> result = new ArrayList<Book>();

        for (Book book : this.books) {
            String owner = book.getOwner();
            if (owner.equals(userNumber)) {
                result.add(book);
            }
        }

        return result;
    }
}
