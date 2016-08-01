package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class LoginOption extends Option {
    public LoginOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.login();
    }
}
