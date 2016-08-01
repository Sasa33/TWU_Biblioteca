package com.twu.biblioteca;

import com.twu.biblioteca.entity.Book;
import com.twu.biblioteca.entity.Movie;
import com.twu.biblioteca.option.*;
import com.twu.biblioteca.repository.BookRepository;
import com.twu.biblioteca.repository.MovieRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;

import static java.util.Arrays.asList;

public class BibliotecaApp {
    private Console console;
    private ItemList<Book> bookList;
    private Menu menu;
    private boolean quit = Boolean.FALSE;
    private ItemList<Movie> movieList;

    public BibliotecaApp(Console console) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookList = new ItemList<Book>(repository.getAllBooks());


        Option option1 = new ListBooksOption(1, "List Books");
        this.menu = new Menu(asList(option1));
    }

    public BibliotecaApp(Console console, Menu menu) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookList = new ItemList<Book>(repository.getAllBooks());

        Movie movie1 = new Movie("Zootopia", "2016", "Byron Howard & Rich Moore", "9.2");
        Movie movie2 = new Movie("The Jungle Book", "2016", "Jon Favreau", "7.9");
        MovieRepository movieRepository = new MovieRepository(asList(movie1, movie2));

        this.movieList = new ItemList<Movie>(movieRepository.getAllMovies());

        this.menu = menu;
    }

    public BibliotecaApp(Console console, Menu menu, ItemList<Book> itemList) {
        this.console = console;

        this.menu = menu;

        this.bookList = itemList;
    }

    public BibliotecaApp(Console console, Menu menu, ItemList<Book> itemList, ItemList<Movie> movieList) {
        this.console = console;

        this.menu = menu;

        this.bookList = itemList;
        this.movieList = movieList;
    }

    public static void main(String[] args) {
        Console console = new Console(new PrintStream(System.out), new BufferedReader(new InputStreamReader(System.in)));
//        BibliotecaApp app = new BibliotecaApp(console);
//        app.start();

        Option option1 = new ListBooksOption(1, "List Books");
        Option option2 = new CheckoutBookOption(2, "Checkout Book");
        Option option3 = new ReturnBookOption(3, "Return Book");
        Option option4 = new ReturnBookOption(4, "Checkout Movie");
        Option option5 = new ReturnBookOption(5, "Return Movie");
        Option option6 = new QuitOption(4, "Quit");
        Menu menu = new Menu(asList(option1, option2, option3, option4, option5, option6));

        BibliotecaApp app = new BibliotecaApp(console, menu);
        app.startWithMenu();
    }

    public void start() {
        console.println("Welcome to the Bangalore Public Library!");

        displayBookListInfo();
    }

    public void startWithMenu() {
        console.println("Welcome to the Bangalore Public Library!");

        while (!quit) {
            displayMenu();
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

    public void displayMenu() {
        console.println("=====================================================================");
        console.println("Please select an option from menu below.");

        List<Option> options = this.menu.getOptions();

        String menus = "Menu: ";

        for (int i = 0; i < options.size(); i++) {
            menus += options.get(i).getOptionInfo();

            if(i < options.size() - 1) {
                menus += " | ";
            }
        }

        console.println(menus);
        console.println("=====================================================================");
    }

    public void displayBookListInfo() {
        console.println("Book List:");

        listAvailableBooks();
    }

    private void listAvailableBooks() {
        List<Book> availableBooks = bookList.getAvailableItems();

        for (int i = 0; i < availableBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableBooks.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void exit() {
        console.println("Thank you for coming to the Bangalore Public Library! See you next time.");

        this.quit = true;
    }

    public void checkoutBook() {
        while(true) {
            int selection = whichBookToCheckout();

            if(this.bookList.checkIfItemExits(selection)) {
                this.bookList.checkoutItem(selection);
                console.println("Thank you! Enjoy the book!");
                break;
            } else {
                console.println("That book is not available. Please choose again!");
            }
        }
    }

    public int whichBookToCheckout() {
        console.println("Which book do you want to checkout:");
        listAvailableBooks();

        return console.getNextInt();
    }

    public void returnBook() {
        while (true) {
            int selection = whichBookToRetrun();

            if (this.bookList.checkIfBookCanBeReturned(selection)) {
                this.bookList.returnItem(selection);
                console.println("Thank you for returning the book!");
                break;
            } else {
                console.println("That is not a valid book to return. Please choose again!");
            }
        }
    }

    public int whichBookToRetrun() {
        console.println("Which book do you want to return:");
        listCheckedOutBooks();

        return console.getNextInt();
    }

    public void listCheckedOutBooks() {
        List<Book> checkedOutBooks = bookList.getCheckedOutItems();

        for (int i = 0; i < checkedOutBooks.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + checkedOutBooks.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void displayMovieListInfo() {
        console.println("Movie List:");

        listAvailableMovies();
    }

    private void listAvailableMovies() {
        List<Movie> availableMovies = movieList.getAvailableItems();

        for (int i = 0; i < availableMovies.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableMovies.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void checkoutMovie() {
        while(true) {
            int selection = whichMovieToCheckout();

            if(this.movieList.checkIfItemExits(selection)) {
                this.movieList.checkoutItem(selection);
                console.println("Thank you! Enjoy the movie!");
                break;
            } else {
                console.println("That movie is not available. Please choose again!");
            }
        }
    }

    private int whichMovieToCheckout() {
        console.println("Which movie do you want to checkout:");
        listAvailableMovies();

        return console.getNextInt();
    }

    public void returnMovie() {
        while (true) {
            int selection = whichMovieToRetrun();

            if (this.movieList.checkIfBookCanBeReturned(selection)) {
                this.movieList.returnItem(selection);
                console.println("Thank you for returning the movie!");
                break;
            } else {
                console.println("That is not a valid movie to return. Please choose again!");
            }
        }
    }

    private int whichMovieToRetrun() {
        console.println("Which movie do you want to return:");
        listCheckedOutMovies();

        return console.getNextInt();
    }

    private void listCheckedOutMovies() {
        List<Movie> checkedOutMovies = movieList.getCheckedOutItems();

        for (int i = 0; i < checkedOutMovies.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + checkedOutMovies.get(i).getDetails();
            console.println(listItem);
        }
    }
}
