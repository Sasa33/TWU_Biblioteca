package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ConsoleTest {
    private Console console;
    private PrintStream printStream;
    private BufferedReader reader;
    private InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        console = new Console(printStream, reader);
        inOrder = inOrder(printStream);
    }

    @Test
    public void should_print_a_string_without_specifing_a_new_line() throws Exception {
        console.print("1234");
        console.print("5678");

        inOrder.verify(printStream, times(1)).print("1234");
        inOrder.verify(printStream, times(1)).print("5678");
    }

    @Test
    public void should_print_a_string_and_specify_a_new_line() throws Exception {
        console.println("1234");
        console.println("5678");

        inOrder.verify(printStream, times(1)).println("1234");
        inOrder.verify(printStream, times(1)).println("5678");
    }

    @Test
    public void should_get_the_user_input_correctly() throws IOException {
        when(reader.readLine()).thenReturn("1");

        assertEquals(1, console.getNextInt());
    }

    @Test
    public void should_getNextString_correctly() throws IOException {
        String expected = "test input";

        when(reader.readLine()).thenReturn(expected);

        assertEquals(expected, console.getNextString());
    }
}