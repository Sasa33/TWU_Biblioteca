package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class ListBooksOption extends Option {
    public ListBooksOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.displayBookListInfo();
    }
}
