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

    public boolean checkoutItem(int chosen, User user) {
        this.availableMovies = repository.getAvailableMovies();

        for (int i = 0; i < this.availableMovies.size(); i++) {
            if (i == chosen - 1) {
                this.availableMovies.get(i).setCheckedOut(true);
                this.availableMovies.get(i).setOwner(user.getNumber());
            }
        }

        return true;
    }

    public boolean checkIfItemExits(int chosen) {
        return chosen > 0 && chosen <= this.availableMovies.size();
    }

    public List<Movie> getCheckedOutMovies() {
        return repository.getCheckedOutMovies();
    }

    public void returnItem(int chosen, User user) {
        this.checkedOutMovies = repository.getCheckedOutMovies();

        for (int i = 0; i < this.checkedOutMovies.size(); i++) {
            if (i == chosen - 1) {
                this.checkedOutMovies.get(i).setCheckedOut(false);
                this.checkedOutMovies.get(i).setOwner("");
            }
        }
    }

    public boolean checkIfItemCanBeReturned(int chosen) {
        return chosen > 0 && chosen <= this.checkedOutMovies.size();
    }

    public boolean isAnyItemCanBeReturned() {
        return !this.checkedOutMovies.isEmpty();
    }

    public boolean isAnyItemCanBeCheckedout() {
        return !this.availableMovies.isEmpty();
    }
}
