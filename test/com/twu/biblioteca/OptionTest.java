package com.twu.biblioteca;

import com.twu.biblioteca.control.BookManager;
import com.twu.biblioteca.control.MovieManager;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.option.*;
import com.twu.biblioteca.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OptionTest {
    private Option option;
    private Console console;
    private InOrder inOrder;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        console = mock(Console.class);
        inOrder = inOrder(console);
        userRepository = new UserRepository();
    }

    @Test
    public void should_initialize_an_option_with_id_and_name_successfully() {
        String optionName = "List Books";
        int optionId = 1;

        option = new ListBooksOption(optionId, optionName);

        assertEquals(optionName, option.getName());
        assertEquals(optionId, option.getId());
    }

    @Test
    public void should_call_listAvailableBooks_method_when_List_Books_option_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);
        BookManager bookManager = mock(BookManager.class);
        when(app.getBookManager()).thenReturn(bookManager);
        when(app.getConsole()).thenReturn(console);

        int optionId = 1;
        String optionName = "List Books";
        option = new ListBooksOption(optionId, optionName);

        option.execute(app);

        verify(bookManager, times(1)).listAvailableBooks(console);
    }

    @Test
    public void should_call_exit_method_when_quit_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);
        when(app.getConsole()).thenReturn(console);

        int optionId = 2;
        String optionName = "Quit";
        option = new QuitOption(optionId, optionName);

        option.execute(app);

        inOrder.verify(console, times(1)).println("Thank you for coming to the Bangalore Public Library! See you next time.");
    }

    @Test
    public void should_call_checkout_book_method_when_checkout_option_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 2;
        String optionName = "Checkout Book";
        option = new CheckoutBookOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).checkoutBook();
    }

    @Test
    public void should_call_returnBook_method_when_return_book_option_is_called() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 3;
        String optionName = "Return Book";
        option = new ReturnBookOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).returnBook();
    }

    @Test
    public void should_call_listAvailableMovies_method_when_List_Movies_option_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);
        MovieManager movieManager = mock(MovieManager.class);
        when(app.getMovieManager()).thenReturn(movieManager);
        when(app.getConsole()).thenReturn(console);

        int optionId = 5;
        String optionName = "List Movies";
        option = new ListMovieOption(optionId, optionName);

        option.execute(app);

        verify(app.getMovieManager(), times(1)).listAvailableMovies(console);
    }

    @Test
    public void should_call_checkoutMovie_method_when_checkout_movie_option_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 4;
        String optionName = "Checkout Movie";
        option = new CheckoutMovieption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).checkoutMovie();
    }

    @Test
    public void should_call_returnMovie_method_when_return_movie_option_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 5;
        String optionName = "Return Movie";
        option = new ReturnMovieOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).returnMovie();
    }

    @Test
    public void should_call_showUserInfo_method_when_userInfoOption_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 6;
        String optionName = "User Info";
        option = new UserInfoOption(optionId, optionName);

        option.execute(app);

    }

    @Test
    public void should_show_login_successful_message_when_login_successfully() {
        BibliotecaApp app = mock(BibliotecaApp.class);
        String expectedNumber = "000-0001";
        String expectedPassword = "123456";
        when(console.getNextString()).thenReturn(expectedNumber, expectedPassword);

        when(app.getConsole()).thenReturn(console);
        when(app.getUserRepository()).thenReturn(userRepository);

        int optionId = 7;
        String optionName = "Login";
        option = new LoginOption(optionId, optionName);

        option.execute(app);

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("Login successful!");
    }

    @Test
    public void should_show_login_failed_message_when_login_failed() {
        BibliotecaApp app = mock(BibliotecaApp.class);
        String expectedNumber = "000-0001";
        String wrongpassword = "wrongpassword";
        String correctPassword = "123456";
        when(console.getNextString()).thenReturn(expectedNumber, wrongpassword, expectedNumber, correctPassword);

        when(app.getConsole()).thenReturn(console);
        when(app.getUserRepository()).thenReturn(userRepository);

        int optionId = 7;
        String optionName = "Login";
        option = new LoginOption(optionId, optionName);

        option.execute(app);

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("No such user or bad password, please login again!");

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("Login successful!");
    }

    @Test
    public void should_show_logout_message_when_logioutOption_is_executed() {
        BibliotecaApp app = mock(BibliotecaApp.class);
        when(app.getConsole()).thenReturn(console);

        int optionId = 8;
        String optionName = "Logout";
        option = new LogoutOption(optionId, optionName);

        option.execute(app);

        inOrder.verify(console, times(1)).println("You are successfully logged out.");
    }
}