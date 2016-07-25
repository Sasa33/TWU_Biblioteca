package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;

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

        listAvailableBooks();
    }

    private void listAvailableBooks() {
        console.println("Book List:");

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        BookList bookList = new BookList(repository.getAllBooks());

        List<Book> availableBooks = bookList.listAllBooks();

        for (int i = 0; i < availableBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableBooks.get(i).getDetails();
            console.println(listItem);
        }
    }
}
