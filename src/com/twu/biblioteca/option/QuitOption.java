package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public class QuitOption extends Option {
    public QuitOption(int id, String optionName) {
        super(id, optionName);
    }

    @Override
    public void execute(BibliotecaApp app) {
        app.getConsole().println("Thank you for coming to the Bangalore Public Library! See you next time.");

        app.setQuit(true);
    }
}
