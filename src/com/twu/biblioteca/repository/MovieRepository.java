package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;

import java.util.List;

public class MovieRepository extends LoanableItemRepositoryImpl<Movie>{
    public MovieRepository(List<Movie> movies) {
        super(movies);
    }

    public List<Movie> getAllMovies() {
        return getAllItems();
    }

    public List<Movie> getAvailableMovies() {
        return getAvailableItems();
    }

    public List<Movie> getCheckedOutMovies() {
        return getCheckedOutItems();
    }

    public List<Movie> findBooksByOwnerNumber(String userNumber) {
        return findItemsByOwnerNumber(userNumber);
    }
}
