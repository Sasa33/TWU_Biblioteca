package com.twu.biblioteca;

import java.util.List;

public class BookRepository {
    private List<Book> repository;

    public BookRepository(List<Book> books) {
        repository = books;
    }

    public List<Book> getAllBooks() {
        return this.repository;
    }
}
