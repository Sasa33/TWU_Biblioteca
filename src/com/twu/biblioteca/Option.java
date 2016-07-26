package com.twu.biblioteca;

public class Option {
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
}
