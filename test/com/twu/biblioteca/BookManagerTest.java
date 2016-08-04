package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookManagerTest {
    private BookManager bookManager;
    private BookRepository repository;
    private List<Book> books;
    private User user;

    @Before
    public void setUp() {
        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        books = asList(book1, book2);

        repository = new BookRepository(books);

        user = mock(User.class);
        when(user.getNumber()).thenReturn("000-0001");
    }

    @Test
    public void should_get_all_the_books_when_book_list_option_is_chosen() {
        bookManager = new BookManager(repository);

        List<Book> availableBooks = bookManager.getAvailableBooks();

        assertEquals(2, availableBooks.size());
        assertEquals(books.get(0), availableBooks.get(0));
        assertEquals(books.get(1), availableBooks.get(1));
    }

    @Test
    public void should_checkout_a_book_successfully_when_checkoutBook_method_is_called() {
        bookManager = new BookManager(repository);

        int chosen = 1;
        bookManager.checkoutBook(chosen, user);

        List<Book> availableBooks1 = bookManager.getAvailableBooks();

        assertEquals(1, availableBooks1.size());
        assertEquals(books.get(1), availableBooks1.get(0));
    }

    @Test
    public void should_put_a_book_into_the_checkedOutBooks_list_when_a_book_is_checked_out() {
        bookManager = new BookManager(repository);

        int chosen = 1;
        bookManager.checkoutBook(chosen, user);

        List<Book> availableBooks = bookManager.getAvailableBooks();
        List<Book> checkedOutBooks = bookManager.getCheckedOutBooks();

        assertEquals(1, availableBooks.size());
        assertEquals(books.get(1), availableBooks.get(0));

        assertEquals(1, checkedOutBooks.size());
        assertEquals(books.get(0), checkedOutBooks.get(0));
    }

    @Test
    public void should_put_a_book_back_to_availableBooks_after_a_book_is_returned() {
        bookManager = new BookManager(repository);

        int chosen = 1;
        bookManager.checkoutBook(chosen, user);

        int index = 1;
        bookManager.returnBook(index);

        List<Book> availableBooks = bookManager.getAvailableBooks();
        List<Book> checkedOutBooks = bookManager.getCheckedOutBooks();


        //after return the checked out book
        assertEquals(2, availableBooks.size());
        assertEquals(books.get(0), availableBooks.get(0));
        assertEquals(books.get(1), availableBooks.get(1));

        assertEquals(true, checkedOutBooks.isEmpty());
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_returned() {
        bookManager = new BookManager(repository);

        boolean isAnyBookCanBeReturned = bookManager.isAnyBookCanBeReturned();

        assertEquals(false, isAnyBookCanBeReturned);
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_checkedout() {
        bookManager = new BookManager(repository);
        int chosen = 1;
        bookManager.checkoutBook(chosen, user);
        bookManager.checkoutBook(chosen, user); // checkout two books

        List<Book> availableBooks = bookManager.getAvailableBooks();

        assertEquals(true, availableBooks.isEmpty());

        boolean isAnyBookCanBeCheckedout = bookManager.isAnyBookCanBeCheckedout();

        assertEquals(false, isAnyBookCanBeCheckedout);
    }
}