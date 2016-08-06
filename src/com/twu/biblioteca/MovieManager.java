package com.twu.biblioteca;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.List;

public class MovieManager extends LoanableItemManagerImpl<Movie>{
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
}
