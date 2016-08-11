package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;
import com.twu.biblioteca.Console;
import com.twu.biblioteca.entity.User;

public class UserInfoOption extends Option {
    public UserInfoOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        User currentUser = app.getCurrentUser();
        Console console = app.getConsole();

        if (currentUser != null) {
            console.println("Name: " + currentUser.getName());
            console.println("Email Address: " + currentUser.getEmail());
            console.println("Phone: " + currentUser.getPhone());
        }
    }
}
