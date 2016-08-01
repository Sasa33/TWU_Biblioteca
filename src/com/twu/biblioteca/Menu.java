package com.twu.biblioteca;

import com.twu.biblioteca.option.Option;

import java.util.List;

public class Menu {
    private List<Option> options;

    public List<Option> getOptions() {
        return options;
    }

    public Menu(List<Option> options) {
        this.options = options;
    }

    public Option getChosenOption(int input) {
        if(validateOption(input) != 0) {
            return this.options.get(input - 1);
        }
        return null;
    }

    public int validateOption(int input) {
        for(Option option : this.options) {
            if(option.getId() == input) {
                return input;
            }
        }
        return 0;
    }
}
