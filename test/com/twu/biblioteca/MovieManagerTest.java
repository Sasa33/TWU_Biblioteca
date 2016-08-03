package com.twu.biblioteca;

import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieManagerTest {
    private MovieManager movieManager;
    private MovieRepository movieRepository;
    private List<Movie> movies;
    private User user;

    @Before
    public void setUp() {
        Movie movie1 = new Movie("Zootopia", "2016", "Byron Howard & Rich Moore", "9.2");
        Movie movie2 = new Movie("The Jungle Book", "2016", "Jon Favreau", "7.9");

        movies = asList(movie1, movie2);

        movieRepository = new MovieRepository(movies);

        user = mock(User.class);
        when(user.getNumber()).thenReturn("000-0001");
    }

    @Test
    public void should_get_all_the_movies_when_getAvailableItems_method_is_called() {

        movieManager = new MovieManager(movieRepository);

        List<Movie> availableMovies = movieManager.getAvailableMovies();

        assertEquals(availableMovies.size(), 2);
        assertEquals(availableMovies.get(0), movies.get(0));
        assertEquals(availableMovies.get(1), movies.get(1));
    }

    @Test
    public void should_put_a_movie_into_the_checkedOutMovie_list_when_a_movie_is_checked_out() {
        movieManager = new MovieManager(movieRepository);

        int chosen = 1;
        movieManager.checkoutMovie(chosen, user);

        List<Movie> availableMovies = movieManager.getAvailableMovies();
        List<Movie> checkedOutMovies = movieManager.getCheckedOutMovies();

        assertEquals(availableMovies.size(), 1);
        assertEquals(availableMovies.get(0), movies.get(1));

        assertEquals(checkedOutMovies.size(), 1);
        assertEquals(checkedOutMovies.get(0), movies.get(0));
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_returned() {
        movieManager = new MovieManager(movieRepository);

        boolean isAnyBookCanBeReturned = movieManager.isAnyMovieCanBeReturned();

        assertEquals(false, isAnyBookCanBeReturned);
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_checkedout() {
        movieManager = new MovieManager(movieRepository);
        int chosen = 1;
        movieManager.checkoutMovie(chosen, user);
        movieManager.checkoutMovie(chosen, user); // checkout two books

        List<Movie> availableMovies = movieManager.getAvailableMovies();

        assertEquals(availableMovies.size(), 0);

        boolean isAnyBookCanBeCheckedout = movieManager.isAnyMovieCanBeCheckedout();

        assertEquals(false, isAnyBookCanBeCheckedout);
    }
}