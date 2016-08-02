package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    public void should_get_books_according_to_isCheckedOut_or_not() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        when(book1.getTitle()).thenReturn("book1");
        when(book2.getTitle()).thenReturn("book2");

        when(book1.isCheckedOut()).thenReturn(true);
        when(book2.isCheckedOut()).thenReturn(false);

        repository = new BookRepository(asList(book1, book2));

        List<Book> availableBooks = repository.getAvailableBooks();
        List<Book> checkedOutBooks = repository.getCheckedOutBooks();

        assertEquals(1, availableBooks.size());
        assertEquals("book1", availableBooks.get(0).getTitle());

        assertEquals(1, checkedOutBooks.size());
        assertEquals("book2", checkedOutBooks.get(0).getTitle());
    }

    @Test
    public void should_find_books_by_owned_user_number() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);
        Book book3 = mock(Book.class);
        when(book1.getTitle()).thenReturn("book1");
        when(book2.getTitle()).thenReturn("book2");
        when(book3.getTitle()).thenReturn("book2");

        String user1Number = "000-0001";
        String user2Number = "000-0002";

        when(book1.getOwner()).thenReturn(user1Number);
        when(book2.getOwner()).thenReturn(user2Number);
        when(book3.getOwner()).thenReturn("");

        repository = new BookRepository(asList(book1, book2, book3));

        List<Book> books = repository.findBooksByOwnerNumber(user1Number);

        assertEquals(1, books.size());
        assertEquals("book1", books.get(0).getTitle());
    }
}