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

    public void displayMenu(Console console) {
        console.println("=====================================================================");
        console.println("Please select an option from menu below.");

        List<Option> options = this.getOptions();

        StringBuilder sb = new StringBuilder();
        String title = "Menu: ";
        String indent = "      ";
        String divider = " | ";
        sb.append(title);

        for (int i = 0; i < options.size(); i++) {
            sb.append(options.get(i).getOptionInfo());

            if ((i+1) % 4 == 0 && i < options.size() - 1) {
                String newLine = System.getProperty("line.separator");
                sb.append(newLine);
                sb.append(indent);
            } else if(i < options.size() - 1) {
                sb.append(divider);
            }
        }
        console.println(sb.toString());
        console.println("=====================================================================");
    }
}
