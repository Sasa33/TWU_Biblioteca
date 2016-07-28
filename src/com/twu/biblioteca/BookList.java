package com.twu.biblioteca;

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
        List<Book> remainingBooks = new ArrayList<Book>();

        for (int i = 0; i < availableBooks.size(); i++) {
            if (i != chosen - 1) {
                remainingBooks.add(availableBooks.get(i));
            } else {
                this.checkedOutBooks.add(availableBooks.get(i));
            }
        }

        this.availableBooks = remainingBooks;
        return true;
    }

    public boolean checkIfBookExits(int chosen) {
        return chosen >= 0 && chosen < this.availableBooks.size();
    }

    public List<Book> getCheckedOutBooks() {
        return this.checkedOutBooks;
    }
}
