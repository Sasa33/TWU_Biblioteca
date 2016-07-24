package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

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

//    @Test
//    public void should_print_a_list_of_all_library_books_after_the_welcome_message() {
//        app = new BibliotecaApp();
//
//        app.start();
//
//        String expected = "Welcome to the Bangalore Public Library!\n" +
//                          "Book List:\n" +
//                          "\t1. Head First Java\n" +
//                          "\t2. Refactoring\n";
//
//        assertEquals(expected, outContent.toString());
//    }
}