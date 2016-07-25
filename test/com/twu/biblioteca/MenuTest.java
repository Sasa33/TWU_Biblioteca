package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuTest {
    private Menu menu;

    @Test
    public void can_initialize_a_menu_with_a_list_of_options_successfully() {
        Option option1 = mock(Option.class);
        Option option2 = mock(Option.class);

        List<Option> options = asList(option1, option2);

        when(option1.getName()).thenReturn("List Books");
        when(option2.getName()).thenReturn("Quit");

        menu = new Menu(options);

        assertEquals(menu.getOptions().size(), 2);
        assertEquals(menu.getOptions().get(0).getName(), "List Books");
        assertEquals(menu.getOptions().get(1).getName(), "Quit");
    }
}