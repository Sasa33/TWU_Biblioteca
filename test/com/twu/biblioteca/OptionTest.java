package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

public class OptionTest {
    private Option option;

    @Test
    public void should_initialize_an_option_with_a_name_successfully() {
        String optionName = "List Books";
        int optionId = 1;

        option = new Option(optionId, optionName);

        assertEquals(optionName, option.getName());
        assertEquals(optionId, option.getId());
    }

    @Test
    public void should_generate_an_id_for_an_option_automatically() {
        int optionId1 = 1;
        String optionName = "List Books";
        int optionId2 = 2;
        String optionName2 = "Quit";

        option = new Option(optionId1, optionName);
        Option option2 = new Option(optionId2, optionName2);

        assertEquals(1, option.getId());
        assertEquals(2, option2.getId());
    }
}