package com.twu.biblioteca.entity;

import com.twu.biblioteca.entity.Book;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    private Book book1;
    private Book book2;

    @Before
    public void setUp() throws Exception {
        book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        book2 = new Book("Refactoring", "Martin Fowler", "1999");
    }

    @Test
    public void should_get_book_each_info_correctly() {
        assertEquals("Refactoring", book2.getTitle());
        assertEquals("Martin Fowler", book2.getAuthor());
        assertEquals("1999", book2.getPublishYear());
    }

    @Test
    public void should_get_book_details_correctly() {
        String details = book1.getDetails();

        String expected = "Head First Java | Kathy Sierra & Bert Bates | 2003";

        assertEquals(expected, details);
    }
}