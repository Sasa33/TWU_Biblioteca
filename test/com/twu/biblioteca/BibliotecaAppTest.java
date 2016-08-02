package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.option.Option;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private BibliotecaApp app;
    private Console console;
    private InOrder inOrder;
    private Menu menu;


    @Before
    public void setUpStreams() {
        console = mock(Console.class);
        inOrder = inOrder(console);

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
    public void should_print_welcome_message_when_the_application_starts() {
        app = new BibliotecaApp(console);

        app.start();

        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
    }

    @Test
    public void should_print_a_list_of_all_library_books_with_book_detail_information_after_welcome_message() {
        app = new BibliotecaApp(console);

        app.start();

        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
        inOrder.verify(console, times(1)).println("Book List:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");
    }

    @Test
    public void should_print_a_menu_with_a_list_of_options_when_call_displayMenu_method() {
        app.displayMenu();

        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. Login | 2. Quit");
        inOrder.verify(console, times(1)).println("=====================================================================");
    }

    @Test
    public void should_show_a_list_of_books_when_displayBookListInfo_method_is_called() {
        app.displayBookListInfo();

        inOrder.verify(console, times(1)).println("Book List:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");
    }

    @Test
    public void should_show_quit_message_when_exit_method_is_called() {
        app.exit();

        inOrder.verify(console, times(1)).println("Thank you for coming to the Bangalore Public Library! See you next time.");
    }

    @Test
    public void should_show_checkout_message_and_list_of_available_books_when_checkoutBook_method_is_called() {
        app.whichBookToCheckout();

        inOrder.verify(console, times(1)).println("Which book do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");
    }

    @Test
    public void should_show_checkout_successful_message_after_checkouting_a_book_successfully() {
        when(console.getNextInt()).thenReturn(1);

        app.checkoutBook();

        inOrder.verify(console, times(1)).println("Which book do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");

        inOrder.verify(console, times(1)).println("Thank you! Enjoy the book!");

        app.displayBookListInfo();

        inOrder.verify(console, times(1)).println("Book List:");
        inOrder.verify(console, times(1)).println("\t1. Refactoring | Martin Fowler | 1999");
    }

    @Test
    public void should_show_checkout_failed_message_after_checkouting_an_invalid_book() {
        when(console.getNextInt()).thenReturn(3, 1);

        app.checkoutBook();

        inOrder.verify(console, times(1)).println("Which book do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");

        inOrder.verify(console, times(1)).println("That book is not available. Please choose again!");

        inOrder.verify(console, times(1)).println("Which book do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");

        inOrder.verify(console, times(1)).println("Thank you! Enjoy the book!");

        app.displayBookListInfo();

        inOrder.verify(console, times(1)).println("Book List:");
        inOrder.verify(console, times(1)).println("\t1. Refactoring | Martin Fowler | 1999");
    }

    @Test
    public void should_show_return_book_message_and_a_list_of_books_that_can_be_returned() {
        Book book1 = new Book("book1", "author1", "2003");
        Book book2 = new Book("book2", "author2", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));

        ItemList bookList = mock(ItemList.class);
        when(bookList.getCheckedOutItems()).thenReturn(repository.getAllBooks());

        app = new BibliotecaApp(console, menu, bookList);

        app.whichBookToRetrun();

        inOrder.verify(console, times(1)).println("Which book do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");
    }

    @Test
    public void should_show_return_successful_message_after_returning_a_book_successfully() {
        Book book1 = new Book("book1", "author1", "2003");
        Book book2 = new Book("book2", "author2", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));

        ItemList bookList = mock(ItemList.class);
        when(bookList.getCheckedOutItems()).thenReturn(repository.getAllBooks());
        when(console.getNextInt()).thenReturn(1);
        when(bookList.checkIfItemCanBeReturned(1)).thenReturn(true);
        when(bookList.isAnyItemCanBeReturned()).thenReturn(true);

        app = new BibliotecaApp(console, menu, bookList);

        app.returnBook();

        inOrder.verify(console, times(1)).println("Which book do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");

        inOrder.verify(console, times(1)).println("Thank you for returning the book!");
    }

    @Test
    public void should_show_return_failed_message_after_returning_an_invalid_book() {
        Book book1 = new Book("book1", "author1", "2003");
        Book book2 = new Book("book2", "author2", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));

        ItemList bookList = mock(ItemList.class);
        when(bookList.getCheckedOutItems()).thenReturn(repository.getAllBooks());
        when(console.getNextInt()).thenReturn(3, 1);
        when(bookList.checkIfItemCanBeReturned(1)).thenReturn(false, true);
        when(bookList.isAnyItemCanBeReturned()).thenReturn(true);

        app = new BibliotecaApp(console, menu, bookList);

        app.returnBook();

        inOrder.verify(console, times(1)).println("Which book do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");

        inOrder.verify(console, times(1)).println("That is not a valid book to return. Please choose again!");

        inOrder.verify(console, times(1)).println("Which book do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. book1 | author1 | 2003");
        inOrder.verify(console, times(1)).println("\t2. book2 | author2 | 1999");

        inOrder.verify(console, times(1)).println("Thank you for returning the book!");
    }

    @Test
    public void should_show_a_list_of_movies_when_displayMovieListInfo_method_is_called() {
        app.displayMovieListInfo();

        inOrder.verify(console, times(1)).println("Movie List:");
        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
        inOrder.verify(console, times(1)).println("\t2. The Jungle Book | 2016 | Jon Favreau | 7.9");
    }

    @Test
    public void should_show_checkout_movie_message_after_checkouting_successfully_or_not() {
        when(console.getNextInt()).thenReturn(4, 2);

        app.checkoutMovie();

        inOrder.verify(console, times(1)).println("Which movie do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
        inOrder.verify(console, times(1)).println("\t2. The Jungle Book | 2016 | Jon Favreau | 7.9");

        inOrder.verify(console, times(1)).println("That movie is not available. Please choose again!");

        inOrder.verify(console, times(1)).println("Which movie do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
        inOrder.verify(console, times(1)).println("\t2. The Jungle Book | 2016 | Jon Favreau | 7.9");

        inOrder.verify(console, times(1)).println("Thank you! Enjoy the movie!");


        app.displayMovieListInfo();

        inOrder.verify(console, times(1)).println("Movie List:");
        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
    }

    @Test
    public void should_show_return_message_after_returning_a_book_successfully_or_not() {
        Movie movie1 = new Movie("movie1", "year1", "author1", "rating1");
        Movie movie2 = new Movie("movie2", "year2", "author2", "rating2");
        MovieRepository repository = new MovieRepository(asList(movie1, movie2));

        ItemList movieList = mock(ItemList.class);
        when(movieList.getCheckedOutItems()).thenReturn(repository.getAllMovies());
        when(console.getNextInt()).thenReturn(4, 1);
        when(movieList.checkIfItemCanBeReturned(1)).thenReturn(false, true);
        when(movieList.isAnyItemCanBeReturned()).thenReturn(true);

        ItemList bookList = mock(ItemList.class);

        app = new BibliotecaApp(console, menu, bookList, movieList);

        app.returnMovie();

        inOrder.verify(console, times(1)).println("Which movie do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. movie1 | year1 | author1 | rating1");
        inOrder.verify(console, times(1)).println("\t2. movie2 | year2 | author2 | rating2");

        inOrder.verify(console, times(1)).println("That is not a valid movie to return. Please choose again!");

        inOrder.verify(console, times(1)).println("Which movie do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. movie1 | year1 | author1 | rating1");
        inOrder.verify(console, times(1)).println("\t2. movie2 | year2 | author2 | rating2");

        inOrder.verify(console, times(1)).println("Thank you for returning the movie!");
    }

    @Test
    public void should_login_successfully_and_set_the_user_as_current_user() {
        String expectedNumber = "000-0001";
        String expectedPassword = "123456";
        when(console.getNextString()).thenReturn(expectedNumber, expectedPassword);

        app.login();

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("Login successful!");

        app.showUserInfo();

        inOrder.verify(console, times(1)).println("Name: user1");
        inOrder.verify(console, times(1)).println("Email Address: user1@thoughtworks.com");
        inOrder.verify(console, times(1)).println("Phone: 13000000000");
    }

    @Test
    public void should_show_login_failed_message() {
        String expectedNumber = "000-0001";
        String wrongpassword = "wrongpassword";
        String correctPassword = "123456";
        when(console.getNextString()).thenReturn(expectedNumber, wrongpassword, expectedNumber, correctPassword);

        app.login();

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("No such user or bad password, please login again!");

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");


        app.showUserInfo();

        inOrder.verify(console, times(1)).println("Name: user1");
        inOrder.verify(console, times(1)).println("Email Address: user1@thoughtworks.com");
        inOrder.verify(console, times(1)).println("Phone: 13000000000");
    }
}