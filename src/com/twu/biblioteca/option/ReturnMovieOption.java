package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;
import com.twu.biblioteca.control.MovieManager;
import com.twu.biblioteca.entity.User;

public class ReturnMovieOption extends Option {
    public ReturnMovieOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        User currentUser = app.getCurrentUser();
        MovieManager movieManager = app.getMovieManager();
        Console console = app.getConsole();

        if (currentUser != null) {
            while (true) {
                if (movieManager.isAnyMovieCanBeReturned()) {
                    int selection = whichMovieToRetrun(console, movieManager);

                    if (movieManager.checkIfMovieCanBeReturned(selection)) {
                        movieManager.returnMovie(selection);
                        console.println("Thank you for returning the movie!");
                        break;
                    } else {
                        console.println("That is not a valid movie to return. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be checked out.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
        }
    }

    private int whichMovieToRetrun(Console console, MovieManager movieManager) {
        console.println("Which movie do you want to return:");
        movieManager.listCheckedOutMovies(console);

        return console.getNextInt();
    }
}
