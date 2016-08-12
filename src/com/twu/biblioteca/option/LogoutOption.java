package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Menu;

import static java.util.Arrays.asList;

public class LogoutOption extends Option {
    public LogoutOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.getConsole().println("You are successfully logged out.");

        app.setCurrentUser(null);

        Option option1 = new LoginOption(1, "Login");
        Option option2 = new ListBooksOption(2, "List Books");
        Option option3 = new CheckoutBookOption(3, "Checkout Book");
        Option option4 = new ReturnBookOption(4, "Return Book");
        Option option5 = new ListMovieOption(5, "List Movies");
        Option option6 = new CheckoutMovieption(6, "Checkout Movie");
        Option option7 = new ReturnMovieOption(7, "Return Movie");
        Option option8 = new QuitOption(8, "Quit");
        Menu menu = new Menu(asList(option1, option2, option3, option4, option5, option6, option7, option8));

        app.setMenu(menu);
    }
}
