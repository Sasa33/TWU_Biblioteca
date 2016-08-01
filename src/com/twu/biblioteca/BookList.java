package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookList {
    private List<Book> availableBooks;
    private List<Book> checkedOutBooks;

    public BookList(List<Book> books) {
        this.availableBooks = books;
        this.checkedOutBooks = new ArrayList<Book>();
    }


    public List<Book> getAvailableBooks() {
        return this.availableBooks;
    }

    public boolean checkoutBook(int chosen) {
//        this.checkedOutBooks.add(this.availableBooks.get(chosen - 1));
//        this.availableBooks.remove(chosen - 1);


        List<Book> remainingBooks = new ArrayList<Book>();

        for (int i = 0; i < this.availableBooks.size(); i++) {
            if (i != chosen - 1) {
                remainingBooks.add(this.availableBooks.get(i));
            } else {
                this.checkedOutBooks.add(this.availableBooks.get(i));
            }
        }

        this.availableBooks = remainingBooks;
        return true;
    }

    public boolean checkIfBookExits(int chosen) {
        return chosen > 0 && chosen <= this.availableBooks.size();
    }

    public List<Book> getCheckedOutBooks() {
        return this.checkedOutBooks;
    }

    public void returnBook(int index) {
//        this.availableBooks.add(this.checkedOutBooks.get(index - 1));
//
//        this.checkedOutBooks.remove(index - 1);


        List<Book> remainingCheckedOutBooks = new ArrayList<Book>();

        for (int i = 0; i < this.checkedOutBooks.size(); i++) {
            if (i != index - 1) {
                remainingCheckedOutBooks.add(this.checkedOutBooks.get(i));
            } else {
                this.availableBooks.add(this.checkedOutBooks.get(i));
            }
        }

        this.checkedOutBooks = remainingCheckedOutBooks;
    }

    public boolean checkIfBookCanBeReturned(int chosen) {
        return chosen > 0 && chosen <= this.checkedOutBooks.size();
    }
}
