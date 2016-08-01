package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class UserInfoOption extends Option {
    public UserInfoOption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.showUserInfo();
    }
}
