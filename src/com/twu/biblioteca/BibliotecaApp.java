package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaApp {
    private Console console;

    public BibliotecaApp(Console console) {
        this.console = console;
    }

    public static void main(String[] args) {
        Console console = new Console(new PrintStream(System.out));
        BibliotecaApp app = new BibliotecaApp(console);

        app.start();
    }

    public void start() {
        console.println("Welcome to the Bangalore Public Library!");

        console.println("Book List:");
        console.println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        console.println("\t2. Refactoring | Martin Fowler | 1999");
    }
}
