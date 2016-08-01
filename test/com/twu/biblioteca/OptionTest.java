package com.twu.biblioteca;

import com.twu.biblioteca.option.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OptionTest {
    private Option option;

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

        int optionId = 1;
        String optionName = "List Books";
        option = new ListBooksOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).displayBookListInfo();
    }

    @Test
    public void should_call_exit_method_when_quit_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 2;
        String optionName = "Quit";
        option = new QuitOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).exit();
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

        int optionId = 5;
        String optionName = "List Movies";
        option = new ListMovieOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).displayMovieListInfo();
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

        verify(app, times(1)).showUserInfo();
    }

    @Test
    public void should_call_login_method_when_loginOption_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 7;
        String optionName = "Login";
        option = new LoginOption(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).login();
    }
}