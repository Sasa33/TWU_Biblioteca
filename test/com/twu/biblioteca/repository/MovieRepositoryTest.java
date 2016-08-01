package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Movie;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
    private MovieRepository repository;

    @Test
    public void should_get_all_movies_correctly() {
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);

        when(movie1.getName()).thenReturn("Zootopia");
        when(movie2.getName()).thenReturn("The Jungle Book");

        repository = new MovieRepository(asList(movie1, movie2));

        List<Movie> movies = repository.getAllMovies();


        assertEquals(movies.size(), 2);
        assertEquals(movies.get(0).getName(), "Zootopia");
        assertEquals(movies.get(1).getName(), "The Jungle Book");
    }
}