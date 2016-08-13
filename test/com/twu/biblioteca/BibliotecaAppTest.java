package com.twu.biblioteca;

import com.twu.biblioteca.control.BookManager;
import com.twu.biblioteca.control.MovieManager;
import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.option.Option;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private BibliotecaApp app;
    private Console console;
    private InOrder inOrder;
    private Menu menu;
    private User user;


    @Before
    public void setUpStreams() {
        console = mock(Console.class);
        inOrder = inOrder(console);

        user = mock(User.class);

        Option option1 = mock(Option.class);
        Option option2 = mock(Option.class);

        List<Option> options = asList(option1, option2);
        when(option1.getOptionInfo()).thenReturn("1. Login");
        when(option2.getOptionInfo()).thenReturn("2. Quit");

        menu = mock(Menu.class);
        when(menu.getOptions()).thenReturn(options);

        app = new BibliotecaApp(console, menu);
    }

    @Test
    public void should_show_return_book_message_and_a_list_of_books_that_can_be_returned() {
        Book book1 = new Book("book1", "author1", "2003");
        Book book2 = new Book("book2", "author2", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));

        BookManager bookList = mock(BookManager.class);
        when(bookList.getCheckedOutBooks()).thenReturn(repository.getAllBooks());

        app = new BibliotecaApp(console, menu, bookList);

        app.whichBookToRetrun();

//        inOrder.verify(console, times(1)).println("Which book do you want to return:");
//        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
//        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");
    }

    @Test
    public void should_show_return_successful_message_after_returning_a_book_successfully() {
        Book book1 = new Book("book1", "author1", "2003");
        Book book2 = new Book("book2", "author2", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));

        BookManager bookList = mock(BookManager.class);
        when(bookList.getCheckedOutBooks()).thenReturn(repository.getAllBooks());
        when(console.getNextInt()).thenReturn(1);
        when(bookList.checkIfBookCanBeReturned(1)).thenReturn(true);
        when(bookList.isAnyBookCanBeReturned()).thenReturn(true);

        app = new BibliotecaApp(console, menu, bookList);

        app.setCurrentUser(user);

        app.returnBook();


//        inOrder.verify(console, times(1)).println("Which book do you want to return:");
//        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
//        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");

        inOrder.verify(console, times(1)).println("Thank you for returning the book!");
    }

    @Test
    public void should_show_return_failed_message_after_returning_an_invalid_book() {
        Book book1 = new Book("book1", "author1", "2003");
        Book book2 = new Book("book2", "author2", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));

        BookManager bookManager = mock(BookManager.class);
        when(bookManager.getCheckedOutBooks()).thenReturn(repository.getAllBooks());
        when(console.getNextInt()).thenReturn(3, 1);
        when(bookManager.checkIfBookCanBeReturned(1)).thenReturn(false, true);
        when(bookManager.isAnyBookCanBeReturned()).thenReturn(true);

        app = new BibliotecaApp(console, menu, bookManager);

        app.setCurrentUser(user);

        app.returnBook();

//        inOrder.verify(console, times(1)).println("Which book do you want to return:");
//        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
//        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");

        inOrder.verify(console, times(1)).println("That is not a valid book to return. Please choose again!");

//        inOrder.verify(console, times(1)).println("Which book do you want to return:");
//        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
//        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");

        inOrder.verify(console, times(1)).println("Thank you for returning the book!");
    }

    @Test
    public void should_show_checkout_movie_message_after_checkouting_successfully_or_not() {
        when(console.getNextInt()).thenReturn(4, 2);

        app.setCurrentUser(user);

        app.checkoutMovie();

        inOrder.verify(console, times(1)).println("Which movie do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
        inOrder.verify(console, times(1)).println("\t2. The Jungle Book | 2016 | Jon Favreau | 7.9");

        inOrder.verify(console, times(1)).println("That movie is not available. Please choose again!");

        inOrder.verify(console, times(1)).println("Which movie do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
        inOrder.verify(console, times(1)).println("\t2. The Jungle Book | 2016 | Jon Favreau | 7.9");

        inOrder.verify(console, times(1)).println("Thank you! Enjoy the movie!");
    }

    @Test
    public void should_show_return_message_after_returning_a_movie_successfully_or_not() {
        Movie movie1 = new Movie("movie1", "year1", "author1", "rating1");
        Movie movie2 = new Movie("movie2", "year2", "author2", "rating2");
        MovieRepository repository = new MovieRepository(asList(movie1, movie2));

        MovieManager movieManager = mock(MovieManager.class);
        when(movieManager.getCheckedOutMovies()).thenReturn(repository.getAllMovies());
        when(console.getNextInt()).thenReturn(4, 1);
        when(movieManager.checkIfMovieCanBeReturned(1)).thenReturn(false, true);
        when(movieManager.isAnyMovieCanBeReturned()).thenReturn(true);

        BookManager bookManager = mock(BookManager.class);

        app = new BibliotecaApp(console, menu, bookManager, movieManager);

        app.setCurrentUser(user);

        app.returnMovie();

//        inOrder.verify(console, times(1)).println("Which movie do you want to return:");
//        inOrder.verify(console, times(1)).println("\t1. movie1 | year1 | author1 | rating1");
//        inOrder.verify(console, times(1)).println("\t2. movie2 | year2 | author2 | rating2");

        inOrder.verify(console, times(1)).println("That is not a valid movie to return. Please choose again!");

//        inOrder.verify(console, times(1)).println("Which movie do you want to return:");
//        inOrder.verify(console, times(1)).println("\t1. movie1 | year1 | author1 | rating1");
//        inOrder.verify(console, times(1)).println("\t2. movie2 | year2 | author2 | rating2");

        inOrder.verify(console, times(1)).println("Thank you for returning the movie!");
    }
}