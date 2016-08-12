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

    public BibliotecaApp(Console console) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookManager = new BookManager(repository);


        Option option1 = new ListBooksOption(1, "List Books");
        this.menu = new Menu(asList(option1));
    }

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
//        BibliotecaApp app = new BibliotecaApp(console);
//        app.start();

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

            if ((i+1) % 4 == 0 && i < options.size() - 1) {
                String newLine = System.getProperty("line.separator");
                menus += newLine;
                menus += "      ";
//                menus += "\n      ";
            } else if(i < options.size() - 1) {
                menus += " | ";
            }
        }

        console.println(menus);
        console.println("=====================================================================");
    }

    public void checkoutBook() {
        if (currentUser != null) {
            while(true) {
                if (bookManager.isAnyBookCanBeCheckedout()) {
                    int selection = whichBookToCheckout();

                    if(this.bookManager.checkIfBookExits(selection)) {
                        this.bookManager.checkoutBook(selection, currentUser);
                        console.println("Thank you! Enjoy the book!");
                        break;
                    } else {
                        console.println("That book is not available. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be checked out.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
            if (login()) {
                checkoutBook();
            }
        }
    }

    public int whichBookToCheckout() {
        console.println("Which book do you want to checkout:");
        bookManager.listAvailableBooks(console);

        return console.getNextInt();
    }

    public void returnBook() {
        if (currentUser != null) {
            while (true) {
                if (bookManager.isAnyBookCanBeReturned()) {
                    int selection = whichBookToRetrun();

                    if (this.bookManager.checkIfBookCanBeReturned(selection)) {
                        this.bookManager.returnBook(selection);
                        console.println("Thank you for returning the book!");
                        break;
                    } else {
                        console.println("That is not a valid book to return. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be returned.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
            if (login()) {
                returnBook();
            }
        }
    }

    public int whichBookToRetrun() {
        console.println("Which book do you want to return:");
        bookManager.listCheckedOutBooks(console);

        return console.getNextInt();
    }

    public void displayMovieListInfo() {
        console.println("Movie List:");

        movieManager.listAvailableMovies(console);
    }

    public void checkoutMovie() {
        if (currentUser != null) {
            while(true) {
                if (movieManager.isAnyMovieCanBeCheckedout()) {
                    int selection = whichMovieToCheckout();

                    if(this.movieManager.checkIfMovieExits(selection)) {
                        this.movieManager.checkoutMovie(selection, currentUser);
                        console.println("Thank you! Enjoy the movie!");
                        break;
                    } else {
                        console.println("That movie is not available. Please choose again!");
                    }
                } else {
                    console.println("Currently, there is no book that can be checked out.");
                    break;
                }
            }
        } else {
            console.println("Please login first...");
            if (login()) {
                checkoutMovie();
            }
        }
    }

    private int whichMovieToCheckout() {
        console.println("Which movie do you want to checkout:");
        movieManager.listAvailableMovies(console);

        return console.getNextInt();
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
            if (login()) {
                returnMovie();
            }
        }
    }

    private int whichMovieToRetrun() {
        console.println("Which movie do you want to return:");
        movieManager.listCheckedOutMovies(console);

        return console.getNextInt();
    }

    public void showUserInfo() {

    }

    public boolean login() {
        while (true) {
            console.print("Library Number: ");
            String number = console.getNextString();

            console.print("Password: ");
            String password = console.getNextString();

            UserRepository userRepository = new UserRepository();

            List<User> users = userRepository.getAllUsers();

            boolean login = false;
            for (User user : users) {
                if (number.equals(user.getNumber()) && password.equals(user.getPassword())) {
                    currentUser = user;
                    login = true;
                    break;
                }
            }

            if (login) {
                console.println("Login successful!");

                Option option1 = new ListBooksOption(1, "List Books");
                Option option2 = new CheckoutBookOption(2, "Checkout Book");
                Option option3 = new ReturnBookOption(3, "Return Book");
                Option option4 = new UserInfoOption(4, "User Info");
                Option option5 = new ListMovieOption(5, "List Movies");
                Option option6 = new CheckoutMovieption(6, "Checkout Movie");
                Option option7 = new ReturnMovieOption(7, "Return Movie");
                Option option8 = new QuitOption(8, "Quit");
                Option option9 = new LogoutOption(9, "Logout");
                Menu menu = new Menu(asList(option1, option2, option3, option4, option5, option6, option7, option8, option9));

                this.setMenu(menu);

                break;
            } else {
                console.println("No such user or bad password, please login again!");
            }
        }
        return true;
    }

    public void logout() {
        console.println("You are successfully logged out.");

        currentUser = null;

        Option option1 = new LoginOption(1, "Login");
        Option option2 = new ListBooksOption(2, "List Books");
        Option option3 = new CheckoutBookOption(3, "Checkout Book");
        Option option4 = new ReturnBookOption(4, "Return Book");
        Option option5 = new ListMovieOption(5, "List Movies");
        Option option6 = new CheckoutMovieption(6, "Checkout Movie");
        Option option7 = new ReturnMovieOption(7, "Return Movie");
        Option option8 = new QuitOption(8, "Quit");
        Menu menu = new Menu(asList(option1, option2, option3, option4, option5, option6, option7, option8));

        this.setMenu(menu);
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

    public void setQuit(boolean quit) {
        this.quit = quit;
    }
}
