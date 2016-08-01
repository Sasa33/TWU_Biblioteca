package com.twu.biblioteca.entity;

import com.twu.biblioteca.entity.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    private Movie movie1;
    private Movie movie2;

    @Before
    public void setUp() throws Exception {
        movie1 = new Movie("Zootopia", "2016", "Byron Howard & Rich Moore", "9.2");
        movie2 = new Movie("The Jungle Book", "2016", "Jon Favreau", "7.9");
    }

    @Test
    public void should_get_book_each_info_correctly() {
        assertEquals("The Jungle Book", movie2.getName());
        assertEquals("2016", movie2.getYear());
        assertEquals("Jon Favreau", movie2.getDirector());
    }

    @Test
    public void should_get_book_details_correctly() {
        String details = movie1.getDetails();

        String expected = "Zootopia | 2016 | Byron Howard & Rich Moore | 9.2";

        assertEquals(expected, details);
    }
}
