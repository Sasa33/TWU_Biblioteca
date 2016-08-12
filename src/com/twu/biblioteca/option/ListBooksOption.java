package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;

public class ListBooksOption extends Option {
    public ListBooksOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        Console console = app.getConsole();

        console.println("Book List:");

        app.getBookManager().listAvailableBooks(console);
    }
}
