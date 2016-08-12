package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;

public class ListMovieOption extends Option {
    public ListMovieOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        Console console = app.getConsole();

        console.println("Movie List:");

        app.getMovieManager().listAvailableMovies(console);
    }
}
