package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class LogoutOption extends Option {
    public LogoutOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.logout();
    }
}
