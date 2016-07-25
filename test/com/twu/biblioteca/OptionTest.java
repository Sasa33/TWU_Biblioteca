package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionTest {
    private Option option;

    @Test
    public void should_initialize_an_option_with_a_name_successfully() {
        String optionName = "List Books";

        option = new Option(optionName);

        assertEquals(optionName, option.getName());
    }

    @Test
    public void should_generate_an_id_for_an_option_automatically() {
        String optionName = "List Books";
        String optionName2 = "Quit";

        option = new Option(optionName);
        Option option2 = new Option(optionName2);

        assertEquals(1, option.getId());
        assertEquals(2, option2.getId());
    }
}