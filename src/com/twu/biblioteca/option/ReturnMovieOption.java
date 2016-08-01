package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class ReturnMovieOption extends Option {
    public ReturnMovieOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.returnMovie();
    }
}
