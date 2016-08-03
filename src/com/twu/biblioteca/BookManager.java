package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.BookRepository;

import java.util.List;

public class BookManager {
    private BookRepository repository;
    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    public BookManager(BookRepository repository) {
        this.repository = repository;
        this.availableBooks = repository.getAvailableBooks();
        this.checkedOutBooks = repository.getCheckedOutBooks();
    }


    public List<Book> getAvailableBooks() {
        return repository.getAvailableBooks();
    }

    public boolean checkoutBook(int chosen, User user) {
        this.availableBooks = repository.getAvailableBooks();

        for (int i = 0; i < this.availableBooks.size(); i++) {
            if (i == chosen - 1) {
                this.availableBooks.get(i).setCheckedOut(true);
                this.availableBooks.get(i).setOwner(user.getNumber());
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
        this.checkedOutBooks = repository.getCheckedOutBooks();

        for (int i = 0; i < this.checkedOutBooks.size(); i++) {
            if (i == chosen - 1) {
                this.checkedOutBooks.get(i).setCheckedOut(false);
                this.checkedOutBooks.get(i).setOwner("");
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
