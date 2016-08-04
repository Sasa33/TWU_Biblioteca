package com.twu.biblioteca;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private MovieRepository repository;

    public MovieManager(MovieRepository repository) {
        this.repository = repository;
    }


    public List<Movie> getAvailableMovies() {
        return repository.getAvailableMovies();
    }

    public boolean checkoutMovie(int chosen, User user) {
        List<Movie> availableMovies = this.getAvailableMovies();

        for (int i = 0; i < availableMovies.size(); i++) {
            if (i == chosen - 1) {
                availableMovies.get(i).setCheckedOut(true);
                availableMovies.get(i).setOwner(user.getNumber());
            }
        }

        return true;
    }

    public boolean checkIfMovieExits(int chosen) {
        return chosen > 0 && chosen <= this.getAvailableMovies().size();
    }

    public List<Movie> getCheckedOutMovies() {
        return repository.getCheckedOutMovies();
    }

    public void returnMovie(int chosen) {
        List<Movie> checkedOutMovies = repository.getCheckedOutMovies();

        for (int i = 0; i < checkedOutMovies.size(); i++) {
            if (i == chosen - 1) {
                checkedOutMovies.get(i).setCheckedOut(false);
                checkedOutMovies.get(i).setOwner("");
            }
        }
    }

    public boolean checkIfMovieCanBeReturned(int chosen) {
        return chosen > 0 && chosen <= this.getCheckedOutMovies().size();
    }

    public boolean isAnyMovieCanBeReturned() {
        return !this.getCheckedOutMovies().isEmpty();
    }

    public boolean isAnyMovieCanBeCheckedout() {
        return !this.getAvailableMovies().isEmpty();
    }
}
