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
}