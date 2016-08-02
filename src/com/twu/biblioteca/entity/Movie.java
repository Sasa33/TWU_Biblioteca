package com.twu.biblioteca.entity;

public class Movie {
    private String name;
    private String year;
    private String director;
    private String rating;
    private boolean checkedOut;

    public Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }

    public String getDetails() {
        return this.getName() + " | " + this.getYear() + " | " + this.getDirector() + " | " + this.getRating();
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }
}
