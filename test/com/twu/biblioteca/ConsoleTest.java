package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ConsoleTest {
    private Console console;
    private PrintStream printStream;
    private InOrder inOrder;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        console = new Console(printStream);
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
}