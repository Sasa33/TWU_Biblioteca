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

    public Option getChosenOption(int input) {
        for(Option option : this.options) {
            if(option.getId() == input) {
                return option;
            }
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
