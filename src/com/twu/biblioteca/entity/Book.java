package com.twu.biblioteca.entity;

public class Book extends AbstracLoanabletItem{
    private String title;
    private String author;
    private String publishYear;

    public Book(String title, String author, String year) {
        this.title = title;
        this.author = author;
        this.publishYear = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getDetails() {
        return this.getTitle() + " | " + this.getAuthor() + " | " + this.getPublishYear();
    }
}
