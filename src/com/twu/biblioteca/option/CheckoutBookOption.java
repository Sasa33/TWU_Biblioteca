package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class CheckoutBookOption extends Option{
    public CheckoutBookOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.checkoutBook();
    }
}
