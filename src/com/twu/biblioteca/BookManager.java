package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

public class BookManager {
    private BookRepository repository;

    public BookManager(BookRepository repository) {
        this.repository = repository;
    }


    public List<Book> getAvailableBooks() {
        return repository.getAvailableBooks();
    }

    public boolean checkoutBook(int chosen, User user) {
        List<Book> availableBooks = this.getAvailableBooks();

        for (int i = 0; i < availableBooks.size(); i++) {
            if (i == chosen - 1) {
                availableBooks.get(i).setCheckedOut(true);
                availableBooks.get(i).setOwner(user.getNumber());
            }
        }

        return true;
    }

    public boolean checkIfBookExits(int chosen) {
        return chosen > 0 && chosen <= this.getAvailableBooks().size();
    }

    public List<Book> getCheckedOutBooks() {
        return repository.getCheckedOutBooks();
    }

    public void returnBook(int chosen) {
        List<Book> checkedOutBooks = repository.getCheckedOutBooks();

        for (int i = 0; i < checkedOutBooks.size(); i++) {
            if (i == chosen - 1) {
                checkedOutBooks.get(i).setCheckedOut(false);
                checkedOutBooks.get(i).setOwner("");
            }
        }
    }

    public boolean checkIfBookCanBeReturned(int chosen) {
        return chosen > 0 && chosen <= this.getCheckedOutBooks().size();
    }

    public boolean isAnyBookCanBeReturned() {
        return !this.getCheckedOutBooks().isEmpty();
    }

    public boolean isAnyBookCanBeCheckedout() {
        return !this.getAvailableBooks().isEmpty();
    }
}
