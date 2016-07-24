package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BibliotecaAppTest {
    private BibliotecaApp app;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void should_print_welcome_message_when_the_application_starts() {
        app = new BibliotecaApp();

        app.start();

        String expected = "Welcome to the Bangalore Public Library!\n";

        assertEquals(expected, outContent.toString());
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