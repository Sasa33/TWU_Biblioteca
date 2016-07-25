package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookListTest {
    private BookList bookList;
    private BookRepository repository;
    private List<Book> books;

    @Before
    public void setUp() {
        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        books = asList(book1, book2);

        repository = mock(BookRepository.class);
    }

    @Test
    public void should_get_all_the_books_when_book_list_option_is_chosen() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new BookList(repository.getAllBooks());

        List<Book> availableBooks = bookList.listAllBooks();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));
    }
}