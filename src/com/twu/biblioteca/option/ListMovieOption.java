package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class ListMovieOption extends Option {
    public ListMovieOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.displayMovieListInfo();
    }
}
