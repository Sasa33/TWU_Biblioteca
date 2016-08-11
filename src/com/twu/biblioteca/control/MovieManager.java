package com.twu.biblioteca.control;

import com.twu.biblioteca.Console;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.List;

public class MovieManager extends LoanableItemManagerImpl<Movie> {
    public MovieManager(MovieRepository repository) {
        super(repository);
    }

    public List<Movie> getAvailableMovies() {
        return getAvailableItems();
    }

    public boolean checkIfMovieExits(int chosen) {
        return checkIfItemExits(chosen);
    }

    public boolean checkoutMovie(int chosen, User user) {
        return checkoutItem(chosen, user);
    }

    public List<Movie> getCheckedOutMovies() {
        return getCheckedOutItems();
    }

    public boolean checkIfMovieCanBeReturned(int chosen) {
        return checkIfItemCanBeReturned(chosen);
    }

    public void returnMovie(int chosen) {
        returnItem(chosen);
    }

    public boolean isAnyMovieCanBeReturned() {
        return isAnyItemCanBeReturned();
    }

    public boolean isAnyMovieCanBeCheckedout() {
        return isAnyItemCanBeCheckedout();
    }

    public void listAvailableMovies(Console console) {
        List<Movie> availableMovies = this.getAvailableMovies();

        for (int i = 0; i < availableMovies.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableMovies.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void listCheckedOutMovies(Console console) {
        List<Movie> checkedOutMovies = this.getCheckedOutMovies();

        for (int i = 0; i < checkedOutMovies.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + checkedOutMovies.get(i).getDetails();
            console.println(listItem);
        }
    }
}
