package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemManagerTest {
    private ItemManager<Book> bookItemManager;
    private BookRepository repository;
    private List<Book> books;
    private ItemManager<Movie> movieItemManager;
    private MovieRepository movieRepository;
    private List<Movie> movies;

    @Before
    public void setUp() {
        Book book1 = mock(Book.class);
        Book book2 = mock(Book.class);

        books = asList(book1, book2);

        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);

        movies = asList(movie1, movie2);

        repository = mock(BookRepository.class);
        movieRepository = mock(MovieRepository.class);
    }

    @Test
    public void should_get_all_the_books_when_book_list_option_is_chosen() {
        when(repository.getAllBooks()).thenReturn(books);

        bookItemManager = new ItemManager<Book>(repository.getAllBooks());

        List<Book> availableBooks = bookItemManager.getAvailableItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));
    }

    @Test
    public void should_checkout_a_book_successfully_when_checkoutBook_method_is_called() {
        when(repository.getAllBooks()).thenReturn(books);

        bookItemManager = new ItemManager<Book>(repository.getAllBooks());

        List<Book> availableBooks = bookItemManager.getAvailableItems();

        int chosen = 1;
        bookItemManager.checkoutItem(chosen);

        List<Book> availableBooks2 = bookItemManager.getAvailableItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));

        assertEquals(availableBooks2.size(), 1);
        assertEquals(availableBooks2.get(0), books.get(1));
    }

    @Test
    public void should_put_a_book_into_the_checkedOutBooks_list_when_a_book_is_checked_out() {
        when(repository.getAllBooks()).thenReturn(books);

        bookItemManager = new ItemManager<Book>(repository.getAllBooks());
        List<Book> availableBooks = bookItemManager.getAvailableItems();

        int chosen = 1;
        bookItemManager.checkoutItem(chosen);

        List<Book> availableBooks2 = bookItemManager.getAvailableItems();
        List<Book> checkedOutBooks = bookItemManager.getCheckedOutItems();

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

        bookItemManager = new ItemManager<Book>(repository.getAllBooks());

        int chosen = 1;
        bookItemManager.checkoutItem(chosen);

        List<Book> checkedOutBooks = bookItemManager.getCheckedOutItems();

        int index = 1;
        bookItemManager.returnItem(index);

        List<Book> availableBooks2 = bookItemManager.getAvailableItems();
        List<Book> checkedOutBooks2 = bookItemManager.getCheckedOutItems();


        // after check out the first book
        assertEquals(checkedOutBooks.size(), 1);
        assertEquals(checkedOutBooks.get(0), books.get(0));

        //after return the checked out book
        assertEquals(availableBooks2.size(), 2);
        assertEquals(availableBooks2.get(0), books.get(1));
        assertEquals(availableBooks2.get(1), books.get(0));

        assertEquals(checkedOutBooks2.isEmpty(), true);
    }

    @Test
    public void should_get_all_the_movies_when_getAvailableItems_method_is_called() {
        when(movieRepository.getAllMovies()).thenReturn(movies);

        movieItemManager = new ItemManager<Movie>(movieRepository.getAllMovies());

        List<Movie> availableMovies = movieItemManager.getAvailableItems();

        assertEquals(availableMovies.size(), 2);
        assertEquals(availableMovies.get(0), movies.get(0));
        assertEquals(availableMovies.get(1), movies.get(1));
    }

    @Test
    public void should_put_a_movie_into_the_checkedOutMovie_list_when_a_movie_is_checked_out() {
        when(movieRepository.getAllMovies()).thenReturn(movies);

        movieItemManager = new ItemManager<Movie>(movieRepository.getAllMovies());
        List<Movie> availableMovies = movieItemManager.getAvailableItems();

        int chosen = 1;
        movieItemManager.checkoutItem(chosen);

        List<Movie> availableMovies2 = movieItemManager.getAvailableItems();
        List<Movie> checkedOutMovies = movieItemManager.getCheckedOutItems();

        assertEquals(availableMovies.size(), 2);
        assertEquals(availableMovies.get(0), movies.get(0));
        assertEquals(availableMovies.get(1), movies.get(1));

        assertEquals(availableMovies2.size(), 1);
        assertEquals(availableMovies2.get(0), movies.get(1));

        assertEquals(checkedOutMovies.size(), 1);
        assertEquals(checkedOutMovies.get(0), movies.get(0));
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_returned() {
        when(repository.getAllBooks()).thenReturn(books);

        bookItemManager = new ItemManager<Book>(repository.getAllBooks());

        boolean isAnyBookCanBeReturned = bookItemManager.isAnyItemCanBeReturned();

        assertEquals(false, isAnyBookCanBeReturned);
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_checkedout() {
        when(repository.getAllBooks()).thenReturn(books);

        bookItemManager = new ItemManager<Book>(repository.getAllBooks());
        int chosen = 1;
        bookItemManager.checkoutItem(chosen);
        bookItemManager.checkoutItem(chosen); // checkout two books

        List<Book> availableBooks = bookItemManager.getAvailableItems();

        assertEquals(availableBooks.size(), 0);

        boolean isAnyBookCanBeCheckedout = bookItemManager.isAnyItemCanBeCheckedout();

        assertEquals(false, isAnyBookCanBeCheckedout);
    }
}