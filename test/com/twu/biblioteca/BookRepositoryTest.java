package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BookRepositoryTest {
    private BookRepository repository;

    @Test
    public void should_get_all_books_correctly() {
        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        repository = new BookRepository(asList(book1, book2));

        List<Book> books = repository.getAllBooks();


        assertEquals(books.size(), 2);
        assertEquals(books.get(0).getTitle(), "Head First Java");
        assertEquals(books.get(1).getTitle(), "Refactoring");
    }

}