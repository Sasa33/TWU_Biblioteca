package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;
import com.twu.biblioteca.control.MovieManager;
import com.twu.biblioteca.entity.User;

public class CheckoutMovieption extends Option {
    public CheckoutMovieption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        User currentUser = app.getCurrentUser();
        MovieManager movieManager = app.getMovieManager();
        Console console = app.getConsole();


        if (currentUser != null) {
            while(true) {
                if (movieManager.isAnyMovieCanBeCheckedout()) {
                    int selection = whichMovieToCheckout(console, movieManager);

                    if(movieManager.checkIfMovieExits(selection)) {
                        movieManager.checkoutMovie(selection, currentUser);
                        console.println("Thank you! Enjoy the movie!");
                        break;
                    } else {
                        console.println("That movie is not available. Please choose again!");
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

    private int whichMovieToCheckout(Console console, MovieManager movieManager) {
        console.println("Which movie do you want to checkout:");
        movieManager.listAvailableMovies(console);

        return console.getNextInt();
    }
}
