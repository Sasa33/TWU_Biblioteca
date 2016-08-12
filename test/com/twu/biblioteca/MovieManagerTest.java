package com.twu.biblioteca;

import com.twu.biblioteca.control.MovieManager;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.MovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MovieManagerTest {
    private MovieManager movieManager;
    private MovieRepository movieRepository;
    private List<Movie> movies;
    private User user;
    private Console console;
    private InOrder inOrder;

    @Before
    public void setUp() {
        Movie movie1 = new Movie("Zootopia", "2016", "Byron Howard & Rich Moore", "9.2");
        Movie movie2 = new Movie("The Jungle Book", "2016", "Jon Favreau", "7.9");

        movies = asList(movie1, movie2);

        movieRepository = new MovieRepository(movies);

        movieManager = new MovieManager(movieRepository);

        user = mock(User.class);
        when(user.getNumber()).thenReturn("000-0001");

        console = mock(Console.class);
        inOrder = inOrder(console);
    }

    @Test
    public void should_get_all_the_movies_when_getAvailableItems_method_is_called() {
        List<Movie> availableMovies = movieManager.getAvailableMovies();

        assertEquals(2, availableMovies.size());
        assertEquals(movies.get(0), availableMovies.get(0));
        assertEquals(movies.get(1), availableMovies.get(1));
    }

    @Test
    public void should_put_a_movie_into_the_checkedOutMovie_list_when_a_movie_is_checked_out() {
        int chosen = 1;
        movieManager.checkoutMovie(chosen, user);

        List<Movie> availableMovies = movieManager.getAvailableMovies();
        List<Movie> checkedOutMovies = movieManager.getCheckedOutMovies();

        assertEquals(1, availableMovies.size());
        assertEquals(movies.get(1), availableMovies.get(0));

        assertEquals(1, checkedOutMovies.size());
        assertEquals(movies.get(0), checkedOutMovies.get(0));
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_returned() {
        boolean isAnyBookCanBeReturned = movieManager.isAnyMovieCanBeReturned();

        assertEquals(false, isAnyBookCanBeReturned);
    }

    @Test
    public void should_return_false_if_there_is_no_item_can_be_checkedout() {
        int chosen = 1;
        movieManager.checkoutMovie(chosen, user);
        movieManager.checkoutMovie(chosen, user); // checkout two books

        List<Movie> availableMovies = movieManager.getAvailableMovies();

        assertEquals(0, availableMovies.size());

        boolean isAnyBookCanBeCheckedout = movieManager.isAnyMovieCanBeCheckedout();

        assertEquals(false, isAnyBookCanBeCheckedout);
    }

    @Test
    public void should_list_the_available_movies_correctly() {
        movieManager.listAvailableMovies(console);

        inOrder.verify(console, times(1)).println("\t1. Zootopia | 2016 | Byron Howard & Rich Moore | 9.2");
        inOrder.verify(console, times(1)).println("\t2. The Jungle Book | 2016 | Jon Favreau | 7.9");
    }
}