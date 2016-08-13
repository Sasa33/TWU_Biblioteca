package com.twu.biblioteca;

import com.twu.biblioteca.control.BookManager;
import com.twu.biblioteca.control.MovieManager;
import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.option.*;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;

public class BibliotecaApp {
    private Console console;
    private BookManager bookManager;
    private Menu menu;
    private boolean quit = Boolean.FALSE;
    private MovieManager movieManager;
    private User currentUser;
    private UserRepository userRepository;


    public BibliotecaApp(Console console, Menu menu) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");
        BookRepository repository = new BookRepository(asList(book1, book2));
        this.bookManager = new BookManager(repository);

        Movie movie1 = new Movie("Zootopia", "2016", "Byron Howard & Rich Moore", "9.2");
        Movie movie2 = new Movie("The Jungle Book", "2016", "Jon Favreau", "7.9");
        MovieRepository movieRepository = new MovieRepository(asList(movie1, movie2));
        this.movieManager = new MovieManager(movieRepository);

        this.menu = menu;

        userRepository = new UserRepository();
    }

    public BibliotecaApp(Console console, Menu menu, BookManager bookManager) {
        this.console = console;
        this.menu = menu;
        this.bookManager = bookManager;
    }

    public BibliotecaApp(Console console, Menu menu, BookManager bookManager, MovieManager movieManager) {
        this.console = console;
        this.menu = menu;
        this.bookManager = bookManager;
        this.movieManager = movieManager;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        Console console = new Console(new PrintStream(System.out), new BufferedReader(new InputStreamReader(System.in)));

        Option option1 = new LoginOption(1, "Login");
        Option option2 = new ListBooksOption(2, "List Books");
        Option option3 = new CheckoutBookOption(3, "Checkout Book");
        Option option4 = new ReturnBookOption(4, "Return Book");
        Option option5 = new ListMovieOption(5, "List Movies");
        Option option6 = new CheckoutMovieption(6, "Checkout Movie");
        Option option7 = new ReturnMovieOption(7, "Return Movie");
        Option option8 = new QuitOption(8, "Quit");
        Menu menu = new Menu(asList(option1, option2, option3, option4, option5, option6, option7, option8));

        BibliotecaApp app = new BibliotecaApp(console, menu);
        
        app.startWithMenu();
    }

    public void startWithMenu() {
        console.println("Welcome to the Bangalore Public Library!");

        while (!quit) {
            this.menu.displayMenu(console);
            int input = console.getNextInt();
            Option chosenOption = menu.getChosenOption(input);
            if(chosenOption != null) {
                chosenOption.execute(this);
            } else {
                displayInvalidOptionMessage();
            }
        }
    }

    private void displayInvalidOptionMessage() {
        console.println("Select a valid option!");
    }

    public void returnMovie() {
        if (currentUser != null) {
            while (true) {
                if (movieManager.isAnyMovieCanBeReturned()) {
                    int selection = whichMovieToRetrun();

                    if (this.movieManager.checkIfMovieCanBeReturned(selection)) {
                        this.movieManager.returnMovie(selection);
                        console.println("Thank you for returning the movie!");
                        break;
                    } else {
                        console.println("That is not a valid movie to return. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be checked out.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
        }
    }

    private int whichMovieToRetrun() {
        console.println("Which movie do you want to return:");
        movieManager.listCheckedOutMovies(console);

        return console.getNextInt();
    }

    public Console getConsole() {
        return console;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public MovieManager getMovieManager() {
        return movieManager;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
