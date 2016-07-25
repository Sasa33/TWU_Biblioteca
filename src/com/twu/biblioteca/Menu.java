package com.twu.biblioteca;

import java.util.List;

public class Menu {
    private List<Option> options;

    public List<Option> getOptions() {
        return options;
    }

    public Menu(List<Option> options) {
        this.options = options;
    }
}
