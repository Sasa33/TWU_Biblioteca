package com.twu.biblioteca.option;

import com.twu.biblioteca.BibliotecaApp;

public abstract class Option {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Option(int id, String optionName) {
        this.id = id;
        this.name = optionName;
    }

    public abstract void execute(BibliotecaApp app);

    public String getOptionInfo() {
        return this.id + ". " + this.name;
    }
}
