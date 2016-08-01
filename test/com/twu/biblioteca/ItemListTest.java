package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemListTest {
    private ItemList itemList;
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

        itemList = new ItemList(repository.getAllBooks());

        List<Book> availableBooks = itemList.getAvailableItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));
    }

    @Test
    public void should_checkout_a_book_successfully_when_checkoutBook_method_is_called() {
        when(repository.getAllBooks()).thenReturn(books);

        itemList = new ItemList(repository.getAllBooks());

        List<Book> availableBooks = itemList.getAvailableItems();

        int chosen = 1;
        itemList.checkoutBook(chosen);

        List<Book> availableBooks2 = itemList.getAvailableItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));

        assertEquals(availableBooks2.size(), 1);
        assertEquals(availableBooks2.get(0), books.get(1));
    }

    @Test
    public void should_put_a_book_into_the_checkedOutBooks_list_when_a_book_is_checked_out() {
        when(repository.getAllBooks()).thenReturn(books);

        itemList = new ItemList(repository.getAllBooks());
        List<Book> availableBooks = itemList.getAvailableItems();

        int chosen = 1;
        itemList.checkoutBook(chosen);

        List<Book> availableBooks2 = itemList.getAvailableItems();
        List<Book> checkedOutBooks = itemList.getCheckedOutItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));

        assertEquals(availableBooks2.size(), 1);
        assertEquals(availableBooks2.get(0), books.get(1));

        assertEquals(checkedOutBooks.size(), 1);
        assertEquals(checkedOutBooks.get(0), books.get(0));
    }

    @Test
    public void should_put_a_book_back_to_availableBooks_after_a_book_is_returned() {
        when(repository.getAllBooks()).thenReturn(books);

        itemList = new ItemList(repository.getAllBooks());

        int chosen = 1;
        itemList.checkoutBook(chosen);

        List<Book> checkedOutBooks = itemList.getCheckedOutItems();

        int index = 1;
        itemList.returnBook(index);

        List<Book> availableBooks2 = itemList.getAvailableItems();
        List<Book> checkedOutBooks2 = itemList.getCheckedOutItems();


        // after check out the first book
        assertEquals(checkedOutBooks.size(), 1);
        assertEquals(checkedOutBooks.get(0), books.get(0));

        //after return the checked out book
        assertEquals(availableBooks2.size(), 2);
        assertEquals(availableBooks2.get(0), books.get(1));
        assertEquals(availableBooks2.get(1), books.get(0));

        assertEquals(checkedOutBooks2.isEmpty(), true);
    }
}