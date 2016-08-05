package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;

import java.util.List;

public class BookRepository extends LoanableItemRepositoryImpl<Book>{
    public BookRepository(List<Book> books) {
        super(books);
    }

    public List<Book> getAllBooks() {
        return getAllItems();
    }

    public List<Book> getAvailableBooks() {
        return getAvailableItems();
    }

    public List<Book> getCheckedOutBooks() {
        return getCheckedOutItems();
    }

    public List<Book> findBooksByOwnerNumber(String userNumber) {
        return findItemsByOwnerNumber(userNumber);
    }
}
