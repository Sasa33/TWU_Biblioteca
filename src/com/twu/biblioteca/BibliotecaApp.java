package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;

public class BibliotecaApp {
    private Console console;
    private BookList bookList;
    private Menu menu;
    private boolean quit = Boolean.FALSE;

    public BibliotecaApp(Console console) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookList = new BookList(repository.getAllBooks());


        Option option1 = new Option(1, "List Books");
        this.menu = new Menu(asList(option1));
    }

    public BibliotecaApp(Console console, Menu menu) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookList = new BookList(repository.getAllBooks());

        this.menu = menu;
    }

    public BibliotecaApp(Console console, Menu menu, BookList bookList) {
        this.console = console;

        this.menu = menu;

        this.bookList = bookList;
    }

    public static void main(String[] args) {
        Console console = new Console(new PrintStream(System.out), new BufferedReader(new InputStreamReader(System.in)));
//        BibliotecaApp app = new BibliotecaApp(console);
//        app.start();

        Option option1 = new Option(1, "List Books");
        Option option2 = new Option(2, "Checkout Book");
        Option option3 = new Option(3, "Return Book");
        Option option4 = new Option(4, "Quit");
        Menu menu = new Menu(asList(option1, option2, option3, option4));

        BibliotecaApp app = new BibliotecaApp(console, menu);
        app.startWithMenu();
    }

    public void start() {
        console.println("Welcome to the Bangalore Public Library!");

        displayBookListInfo();
    }

    public void startWithMenu() {
        console.println("Welcome to the Bangalore Public Library!");

        while (!quit) {
            displayMenu();
            int input = console.getNextInt();
            Option chosenOption = menu.getChosenOption(input);
            if(chosenOption != null) {
                chosenOption.execute(this);
            } else {
                displayInvalidOptionMessage();
            }
        }
    }

    private void displayInvalidOptionMessage() {
        console.println("Select a valid option!");
    }

    public void displayMenu() {
        console.println("===========================================================");
        console.println("Please select an option from menu below.");

        List<Option> options = this.menu.getOptions();

        String menus = "Menu: ";

        for (int i = 0; i < options.size(); i++) {
            menus += options.get(i).getId() + ". " + options.get(i).getName();

            if(i < options.size() - 1) {
                menus += " | ";
            }
        }

        console.println(menus);
        console.println("===========================================================");
    }

    public void displayBookListInfo() {
        console.println("Book List:");

        listAvailableBooks();
    }

    private void listAvailableBooks() {
        List<Book> availableBooks = bookList.getAvailableBooks();

        for (int i = 0; i < availableBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableBooks.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void exit() {
        console.println("Thank you for coming to the Bangalore Public Library! See you next time.");

        this.quit = true;
    }

    public void checkoutBook() {
        while(true) {
            int selection = whichBookToCheckout();

            if(this.bookList.checkIfBookExits(selection)) {
                this.bookList.checkoutBook(selection);
                console.println("Thank you! Enjoy the book!");
                break;
            } else {
                console.println("That book is not available. Please choose again!");
            }
        }
    }

    public int whichBookToCheckout() {
        console.println("Which book do you want to checkout:");
        listAvailableBooks();

        return console.getNextInt();
    }

    public void returnBook() {
        int selection = whichBookToRetrun();

        if (this.bookList.checkIfBookCanBeReturned(selection)) {
            this.bookList.returnBook(selection);
            console.println("Thank you for returning the book!");
        }
    }

    public int whichBookToRetrun() {
        console.println("Which book do you want to return:");
        listCheckedOutBooks();

        return console.getNextInt();
    }

    public void listCheckedOutBooks() {
        List<Book> checkedOutBooks = bookList.getCheckedOutBooks();

        for (int i = 0; i < checkedOutBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + checkedOutBooks.get(i).getDetails();
            console.println(listItem);
        }
    }
}
