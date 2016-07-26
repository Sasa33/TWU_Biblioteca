package com.twu.biblioteca;

public class Option {
    private int id;
    private String name;

    private static int num = 0;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Option(String optionName) {
        this.id = ++num;
        this.name = optionName;
    }
}
