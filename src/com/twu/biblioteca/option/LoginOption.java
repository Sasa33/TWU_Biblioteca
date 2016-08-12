package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;
import com.twu.biblioteca.Menu;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.UserRepository;

import java.util.List;

import static java.util.Arrays.asList;

public class LoginOption extends Option {
    public LoginOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        Console console = app.getConsole();

        while (true) {
            console.print("Library Number: ");
            String number = console.getNextString();

            console.print("Password: ");
            String password = console.getNextString();

            List<User> users = app.getUserRepository().getAllUsers();

            boolean login = false;
            for (User user : users) {
                if (number.equals(user.getNumber()) && password.equals(user.getPassword())) {
                    app.setCurrentUser(user);
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

                app.setMenu(menu);

                break;
            } else {
                console.println("No such user or bad password, please login again!");
            }
        }
    }
}
