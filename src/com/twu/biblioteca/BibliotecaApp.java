package com.twu.biblioteca;

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
    private ItemManager<Book> bookItemManager;
    private Menu menu;
    private boolean quit = Boolean.FALSE;
    private ItemManager<Movie> movieItemManager;
    private User currentUser;

    public BibliotecaApp(Console console) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookItemManager = new ItemManager<Book>(repository.getAllBooks());


        Option option1 = new ListBooksOption(1, "List Books");
        this.menu = new Menu(asList(option1));
    }

    public BibliotecaApp(Console console, Menu menu) {
        this.console = console;

        Book book1 = new Book("Head First Java", "Kathy Sierra & Bert Bates", "2003");
        Book book2 = new Book("Refactoring", "Martin Fowler", "1999");

        BookRepository repository = new BookRepository(asList(book1, book2));

        this.bookItemManager = new ItemManager<Book>(repository.getAllBooks());

        Movie movie1 = new Movie("Zootopia", "2016", "Byron Howard & Rich Moore", "9.2");
        Movie movie2 = new Movie("The Jungle Book", "2016", "Jon Favreau", "7.9");
        MovieRepository movieRepository = new MovieRepository(asList(movie1, movie2));

        this.movieItemManager = new ItemManager<Movie>(movieRepository.getAllMovies());

        this.menu = menu;
    }

    public BibliotecaApp(Console console, Menu menu, ItemManager<Book> itemManager) {
        this.console = console;

        this.menu = menu;

        this.bookItemManager = itemManager;
    }

    public BibliotecaApp(Console console, Menu menu, ItemManager<Book> itemManager, ItemManager<Movie> movieItemManager) {
        this.console = console;

        this.menu = menu;

        this.bookItemManager = itemManager;
        this.movieItemManager = movieItemManager;
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

    public void displayBookListInfo() {
        console.println("Book List:");

        listAvailableBooks();
    }

    private void listAvailableBooks() {
        List<Book> availableBooks = bookItemManager.getAvailableItems();

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
        if (currentUser != null) {
            while(true) {
                if (bookItemManager.isAnyItemCanBeCheckedout()) {
                    int selection = whichBookToCheckout();

                    if(this.bookItemManager.checkIfItemExits(selection)) {
                        this.bookItemManager.checkoutItem(selection);
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
        listAvailableBooks();

        return console.getNextInt();
    }

    public void returnBook() {
        if (currentUser != null) {
            while (true) {
                if (bookItemManager.isAnyItemCanBeReturned()) {
                    int selection = whichBookToRetrun();

                    if (this.bookItemManager.checkIfItemCanBeReturned(selection)) {
                        this.bookItemManager.returnItem(selection);
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
        listCheckedOutBooks();

        return console.getNextInt();
    }

    public void listCheckedOutBooks() {
        List<Book> checkedOutBooks = bookItemManager.getCheckedOutItems();

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
        List<Movie> availableMovies = movieItemManager.getAvailableItems();

        for (int i = 0; i < availableMovies.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + availableMovies.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void checkoutMovie() {
        if (currentUser != null) {
            while(true) {
                if (movieItemManager.isAnyItemCanBeCheckedout()) {
                    int selection = whichMovieToCheckout();

                    if(this.movieItemManager.checkIfItemExits(selection)) {
                        this.movieItemManager.checkoutItem(selection);
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
        listAvailableMovies();

        return console.getNextInt();
    }

    public void returnMovie() {
        if (currentUser != null) {
            while (true) {
                if (movieItemManager.isAnyItemCanBeReturned()) {
                    int selection = whichMovieToRetrun();

                    if (this.movieItemManager.checkIfItemCanBeReturned(selection)) {
                        this.movieItemManager.returnItem(selection);
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
        listCheckedOutMovies();

        return console.getNextInt();
    }

    private void listCheckedOutMovies() {
        List<Movie> checkedOutMovies = movieItemManager.getCheckedOutItems();

        for (int i = 0; i < checkedOutMovies.size(); i++) {
            String listItem = "\t" + ( i + 1 ) + ". " + checkedOutMovies.get(i).getDetails();
            console.println(listItem);
        }
    }

    public void showUserInfo() {
        if (currentUser != null) {
            console.println("Name: " + currentUser.getName());
            console.println("Email Address: " + currentUser.getEmail());
            console.println("Phone: " + currentUser.getPhone());
        }
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
                Menu menu = new Menu(asList(option1, option2, option3, option4, option5, option6, option7, option8));

                this.setMenu(menu);

                break;
            } else {
                console.println("No such user or bad password, please login again!");
            }
        }
        return true;
    }

    public void logout() {
        
    }
}
