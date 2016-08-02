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

    @Test
    public void should_get_movies_according_to_isCheckedOut_or_not() {
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);
        when(movie1.getName()).thenReturn("movie1");
        when(movie2.getName()).thenReturn("movie2");

        when(movie1.isCheckedOut()).thenReturn(true);
        when(movie2.isCheckedOut()).thenReturn(false);

        repository = new MovieRepository(asList(movie1, movie2));

        List<Movie> availableMovies = repository.getAvailableMovies();
        List<Movie> checkedOutMovies = repository.getCheckedOutMovies();

        assertEquals(1, availableMovies.size());
        assertEquals("movie2", availableMovies.get(0).getName());

        assertEquals(1, checkedOutMovies.size());
        assertEquals("movie1", checkedOutMovies.get(0).getName());
    }
}