package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;

import java.util.List;

public class MovieRepository {
    private List<Movie> repository;

    public MovieRepository(List<Movie> movies) {
        repository = movies;
    }

    public List<Movie> getAllBooks() {
        return this.repository;
    }

    public List<Movie> getAllMovies() {
        return this.repository;
    }
}
