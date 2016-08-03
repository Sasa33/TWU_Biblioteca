package com.twu.biblioteca;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.MovieRepository;

import java.util.List;

public class MovieManager {
    private MovieRepository repository;
    private List<Movie> availableMovies;
    private List<Movie> checkedOutMovies;

    public MovieManager(MovieRepository repository) {
        this.repository = repository;
        this.availableMovies = repository.getAvailableMovies();
        this.checkedOutMovies = repository.getCheckedOutMovies();
    }


    public List<Movie> getAvailableMovies() {
        return repository.getAvailableMovies();
    }

    public boolean checkoutMovie(int chosen, User user) {
        this.availableMovies = repository.getAvailableMovies();

        for (int i = 0; i < this.availableMovies.size(); i++) {
            if (i == chosen - 1) {
                this.availableMovies.get(i).setCheckedOut(true);
                this.availableMovies.get(i).setOwner(user.getNumber());
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
        this.checkedOutMovies = repository.getCheckedOutMovies();

        for (int i = 0; i < this.checkedOutMovies.size(); i++) {
            if (i == chosen - 1) {
                this.checkedOutMovies.get(i).setCheckedOut(false);
                this.checkedOutMovies.get(i).setOwner("");
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
