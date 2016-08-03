package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private List<Movie> movies;
    private List<Movie> availableMovies = new ArrayList<Movie>();
    private List<Movie> checkedOutMovies  = new ArrayList<Movie>();

    public MovieRepository(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getAllMovies() {
        return this.movies;
    }

    public List<Movie> getAvailableMovies() {
        availableMovies.clear();
        checkedOutMovies.clear();
        for (Movie movie : movies) {
            if (movie.isCheckedOut()) {
                checkedOutMovies.add(movie);
            } else {
                availableMovies.add(movie);
            }
        }
        return availableMovies;
    }

    public List<Movie> getCheckedOutMovies() {
        availableMovies.clear();
        checkedOutMovies.clear();
        for (Movie movie : movies) {
            if (movie.isCheckedOut()) {
                checkedOutMovies.add(movie);
            } else {
                availableMovies.add(movie);
            }
        }
        return checkedOutMovies;
    }

    public List<Movie> findBooksByOwnerNumber(String userNumber) {
        List<Movie> result = new ArrayList<Movie>();

        for (Movie movie : this.movies) {
            String owner = movie.getOwner();
            if (owner.equals(userNumber)) {
                result.add(movie);
            }
        }

        return result;
    }
}
