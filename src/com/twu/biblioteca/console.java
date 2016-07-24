package com.twu.biblioteca;

import java.io.PrintStream;

public class Console {
    private final PrintStream printStream;

    public Console(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void print(String message) {
        printStream.print(message);
    }

    public void println(String message) {
        printStream.println(message);
    }
}
