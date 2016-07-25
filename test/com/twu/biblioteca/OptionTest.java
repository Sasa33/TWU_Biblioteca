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
}