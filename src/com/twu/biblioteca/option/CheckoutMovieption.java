package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class CheckoutMovieption extends Option {
    public CheckoutMovieption(int optionId, String optionName) {
        super(optionId, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.checkoutMovie();
    }
}
