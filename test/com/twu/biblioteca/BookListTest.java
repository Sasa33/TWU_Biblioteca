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
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        books = asList(book1, book2);



        repository = mock(BookRepository.class);
    }

    @Test
    public void should_get_all_the_books_when_book_list_option_is_chosen() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new BookList(repository.getAllBooks());

        List<Book> availableBooks = bookList.getAvailableBooks();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));
    }

    @Test
    public void should_checkout_a_book_successfully_when_checkoutBook_method_is_called() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new BookList(repository.getAllBooks());

        List<Book> availableBooks = bookList.getAvailableBooks();

        int chosen = 1;
        bookList.checkoutBook(chosen);

        List<Book> availableBooks2 = bookList.getAvailableBooks();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));

        assertEquals(availableBooks2.size(), 1);
        assertEquals(availableBooks2.get(0), books.get(1));
    }

    @Test
    public void should_put_a_book_into_the_checkedOutBooks_list_when_a_book_is_checked_out() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new BookList(repository.getAllBooks());
        List<Book> availableBooks = bookList.getAvailableBooks();

        int chosen = 1;
        bookList.checkoutBook(chosen);

        List<Book> availableBooks2 = bookList.getAvailableBooks();
        List<Book> checkedOutBooks = bookList.getCheckedOutBooks();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));

        assertEquals(availableBooks2.size(), 1);
        assertEquals(availableBooks2.get(0), books.get(1));

        assertEquals(checkedOutBooks.size(), 1);
        assertEquals(checkedOutBooks.get(0), books.get(0));
    }
}