package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;
import com.twu.biblioteca.control.BookManager;
import com.twu.biblioteca.entity.User;

public class ReturnBookOption extends Option {
    public ReturnBookOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        User currentUser = app.getCurrentUser();
        BookManager bookManager = app.getBookManager();
        Console console = app.getConsole();

        if (currentUser != null) {
            while (true) {
                if (bookManager.isAnyBookCanBeReturned()) {
                    int selection = whichBookToRetrun(console, bookManager);

                    if (bookManager.checkIfBookCanBeReturned(selection)) {
                        bookManager.returnBook(selection);
                        console.println("Thank you for returning the book!");
                        break;
                    } else {
                        console.println("That is not a valid book to return. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be returned.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
        }
    }

    private int whichBookToRetrun(Console console, BookManager bookManager) {
        console.println("Which book do you want to return:");
        bookManager.listCheckedOutBooks(console);

        return console.getNextInt();
    }
}
