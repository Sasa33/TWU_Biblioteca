package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;
import com.twu.biblioteca.control.BookManager;
import com.twu.biblioteca.entity.User;

public class CheckoutBookOption extends Option{
    public CheckoutBookOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        User currentUser = app.getCurrentUser();
        BookManager bookManager = app.getBookManager();
        Console console = app.getConsole();

        if (currentUser != null) {
            while(true) {
                if (bookManager.isAnyBookCanBeCheckedout()) {
                    int selection = whichBookToCheckout(console, bookManager);

                    if(bookManager.checkIfBookExits(selection)) {
                        bookManager.checkoutBook(selection, currentUser);
                        console.println("Thank you! Enjoy the book!");
                        break;
                    } else {
                        console.println("That book is not available. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be checked out.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
        }
    }

    private int whichBookToCheckout(Console console, BookManager bookManager) {
        console.println("Which book do you want to checkout:");
        bookManager.listAvailableBooks(console);

        return console.getNextInt();
    }
}
