package com.twu.biblioteca.control;

import com.twu.biblioteca.Console;
import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

public class BookManager extends LoanableItemManagerImpl<Book> {
    public BookManager(BookRepository repository) {
        super(repository);
    }


    public List<Book> getAvailableBooks() {
        return getAvailableItems();
    }

    public boolean checkIfBookExits(int chosen) {
        return checkIfItemExits(chosen);
    }

    public boolean checkoutBook(int chosen, User user) {
        return checkoutItem(chosen,user);
    }

    public List<Book> getCheckedOutBooks() {
        return getCheckedOutItems();
    }

    public boolean checkIfBookCanBeReturned(int chosen) {
        return checkIfItemCanBeReturned(chosen);
    }

    public void returnBook(int chosen) {
        returnItem(chosen);
    }

    public boolean isAnyBookCanBeReturned() {
        return isAnyItemCanBeReturned();
    }

    public boolean isAnyBookCanBeCheckedout() {
        return isAnyItemCanBeCheckedout();
    }

    public void listAvailableBooks(Console console) {
        List<Book> availableBooks = this.getAvailableBooks();

        for (int i = 0; i < availableBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableBooks.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void listCheckedOutBooks(Console console) {
        List<Book> checkedOutBooks = this.getCheckedOutBooks();

        for (int i = 0; i < checkedOutBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + checkedOutBooks.get(i).getDetails();
            console.println(listItem);
        }
    }
}