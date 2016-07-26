package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {
    private BibliotecaApp app;
    private Console console;
    private InOrder inOrder;


    @Before
    public void setUpStreams() {
        console = mock(Console.class);
        inOrder = inOrder(console);
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

//    @Test
//    public void should_print_a_menu_with_a_list_of_options_after_welcome_message() {
//        Option option1 = mock(Option.class);
//        Option option2 = mock(Option.class);
//
//        List<Option> options = asList(option1, option2);
//        when(option1.getName()).thenReturn("List Books");
//        when(option1.getId()).thenReturn(1);
//        when(option2.getName()).thenReturn("Quit");
//        when(option2.getId()).thenReturn(2);
//
//        Menu menu = mock(Menu.class);
//        when(menu.getOptions()).thenReturn(options);
//        when(console.getNextInt()).thenReturn(1);
//        when(menu.getChosenOption(1)).thenReturn(option1);
//
//        app = new BibliotecaApp(console, menu);
//
//        app.startWithMenu();
//
//        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
//        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
//        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Quit");
//    }

//    @Test
//    public void should_show_a_list_of_books_after_choose_list_books_option_when_start_with_menu() {
//        Option option1 = new Option(1, "List Books");
//        Option option2 = new Option(2, "Quit");
//
//        List<Option> options = asList(option1, option2);
//
//        Menu menu = mock(Menu.class);
//        when(menu.getOptions()).thenReturn(options);
//        when(console.getNextInt()).thenReturn(1);
//        when(menu.getChosenOption(1)).thenReturn(option1);
//
//        app = new BibliotecaApp(console, menu);
//
//        app.startWithMenu();
//
//        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
//        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
//        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Quit");
//        inOrder.verify(console, times(1)).println("Book List:");
//        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
//        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");
//    }
//
//    @Test
//    public void should_quit_the_library_when_choose_quit_option() {
//        Option option1 = new Option(1, "List Books");
//        Option option2 = new Option(2, "Quit");
//
//        List<Option> options = asList(option1, option2);
//
//        Menu menu = mock(Menu.class);
//        when(menu.getOptions()).thenReturn(options);
//        when(console.getNextInt()).thenReturn(2);
//        when(menu.getChosenOption(2)).thenReturn(option2);
//
//        app = new BibliotecaApp(console, menu);
//
//        app.startWithMenu();
//
//        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
//        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
//        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Quit");
//        inOrder.verify(console, times(1)).println("Thank you for coming to the Bangalore Public Library! See you next time.");
//    }
}