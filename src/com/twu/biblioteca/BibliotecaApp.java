package com.twu.biblioteca;

public class BibliotecaApp {
    private Console console;

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();

        app.start();
    }

    public void start() {
        console.println("Welcome to the Bangalore Public Library!");

        console.println("Book List:");
        console.println("\t1. Head First Java");
        console.println("\t2. Refactoring");
    }
}
