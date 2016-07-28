package com.twu.biblioteca;

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


    @Before
    public void setUpStreams() {
        console = mock(Console.class);
        inOrder = inOrder(console);

        Option option1 = mock(Option.class);
        Option option2 = mock(Option.class);

        List<Option> options = asList(option1, option2);
        when(option1.getName()).thenReturn("List Books");
        when(option1.getId()).thenReturn(1);
        when(option2.getName()).thenReturn("Quit");
        when(option2.getId()).thenReturn(2);

        Menu menu = mock(Menu.class);
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

        inOrder.verify(console, times(1)).println("===========================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Quit");
        inOrder.verify(console, times(1)).println("===========================================================");
    }

    @Test
    public void should_show_a_list_of_books_when_listAvailableBooks_method_is_called() {
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
}