package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class ReturnBookOption extends Option {
    public ReturnBookOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.returnBook();
    }
}
