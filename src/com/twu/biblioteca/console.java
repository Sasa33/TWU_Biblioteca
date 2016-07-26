package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Console {
    private final PrintStream printStream;
    private final BufferedReader reader;

    public Console(PrintStream printStream, BufferedReader reader) {
        this.printStream = printStream;
        this.reader = reader;
    }

    public int getNextInt() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void print(String message) {
        printStream.print(message);
    }

    public void println(String message) {
        printStream.println(message);
    }
}
