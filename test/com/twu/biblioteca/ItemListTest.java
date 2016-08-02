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

public class ItemListTest {
    private ItemList<Book> bookList;
    private BookRepository repository;
    private List<Book> books;
    private ItemList<Movie> movieList;
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

        bookList = new ItemList<Book>(repository.getAllBooks());

        List<Book> availableBooks = bookList.getAvailableItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));
    }

    @Test
    public void should_checkout_a_book_successfully_when_checkoutBook_method_is_called() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new ItemList<Book>(repository.getAllBooks());

        List<Book> availableBooks = bookList.getAvailableItems();

        int chosen = 1;
        bookList.checkoutItem(chosen);

        List<Book> availableBooks2 = bookList.getAvailableItems();

        assertEquals(availableBooks.size(), 2);
        assertEquals(availableBooks.get(0), books.get(0));
        assertEquals(availableBooks.get(1), books.get(1));

        assertEquals(availableBooks2.size(), 1);
        assertEquals(availableBooks2.get(0), books.get(1));
    }

    @Test
    public void should_put_a_book_into_the_checkedOutBooks_list_when_a_book_is_checked_out() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new ItemList<Book>(repository.getAllBooks());
        List<Book> availableBooks = bookList.getAvailableItems();

        int chosen = 1;
        bookList.checkoutItem(chosen);

        List<Book> availableBooks2 = bookList.getAvailableItems();
        List<Book> checkedOutBooks = bookList.getCheckedOutItems();

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

        bookList = new ItemList<Book>(repository.getAllBooks());

        int chosen = 1;
        bookList.checkoutItem(chosen);

        List<Book> checkedOutBooks = bookList.getCheckedOutItems();

        int index = 1;
        bookList.returnItem(index);

        List<Book> availableBooks2 = bookList.getAvailableItems();
        List<Book> checkedOutBooks2 = bookList.getCheckedOutItems();


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

        movieList = new ItemList<Movie>(movieRepository.getAllMovies());

        List<Movie> availableMovies = movieList.getAvailableItems();

        assertEquals(availableMovies.size(), 2);
        assertEquals(availableMovies.get(0), movies.get(0));
        assertEquals(availableMovies.get(1), movies.get(1));
    }

    @Test
    public void should_put_a_movie_into_the_checkedOutMovie_list_when_a_movie_is_checked_out() {
        when(movieRepository.getAllMovies()).thenReturn(movies);

        movieList = new ItemList<Movie>(movieRepository.getAllMovies());
        List<Movie> availableMovies = movieList.getAvailableItems();

        int chosen = 1;
        movieList.checkoutItem(chosen);

        List<Movie> availableMovies2 = movieList.getAvailableItems();
        List<Movie> checkedOutMovies = movieList.getCheckedOutItems();

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

        bookList = new ItemList<Book>(repository.getAllBooks());

        boolean isAnyBookCanBeReturned = bookList.isAnyItemCanBeReturned();

        assertEquals(false, isAnyBookCanBeReturned);
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_checkedout() {
        when(repository.getAllBooks()).thenReturn(books);

        bookList = new ItemList<Book>(repository.getAllBooks());
        int chosen = 1;
        bookList.checkoutItem(chosen);
        bookList.checkoutItem(chosen); // checkout two books

        List<Book> availableBooks = bookList.getAvailableItems();

        assertEquals(availableBooks.size(), 0);

        boolean isAnyBookCanBeCheckedout = bookList.isAnyItemCanBeCheckedout();

        assertEquals(false, isAnyBookCanBeCheckedout);
    }
}