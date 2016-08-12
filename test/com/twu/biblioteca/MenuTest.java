package com.twu.biblioteca;

import com.twu.biblioteca.option.Option;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTest {
    private Menu menu;
    private Console console;
    private InOrder inOrder;
    private Option option1;
    private Option option2;

    @Before
    public void setUp() {
        console = mock(Console.class);
        inOrder = inOrder(console);

        option1 = mock(Option.class);
        option2 = mock(Option.class);

        List<Option> options = asList(option1, option2);

        when(option1.getName()).thenReturn("List Books");
        when(option1.getId()).thenReturn(1);
        when(option1.getOptionInfo()).thenReturn("1. List Books");
        when(option2.getName()).thenReturn("Quit");
        when(option2.getId()).thenReturn(2);
        when(option2.getOptionInfo()).thenReturn("2. Quit");

        menu = new Menu(options);
    }

    @Test
    public void can_initialize_a_menu_with_a_list_of_options_successfully() {
        assertEquals(menu.getOptions().size(), 2);
        assertEquals(menu.getOptions().get(0).getName(), "List Books");
        assertEquals(menu.getOptions().get(1).getName(), "Quit");
    }

    @Test
    public void should_filter_the_chosen_option_when_input_an_option() {
        int input = 1;
        Option chosenOption = menu.getChosenOption(input);

        assertEquals(option1, chosenOption);
    }

    @Test
    public void should_check_if_the_chosen_option_is_valid() {
        int input = 3;

        assertEquals(0, menu.validateOption(input));
    }

    @Test
    public void should_print_a_menu_with_a_list_of_options_when_call_displayMenu_method() {
        Option option3 = mock(Option.class);
        Option option4 = mock(Option.class);
        Option option5 = mock(Option.class);

        List<Option> options = asList(option1, option2, option3, option4, option5);

        when(option3.getName()).thenReturn("Checkout Book");
        when(option3.getId()).thenReturn(3);
        when(option3.getOptionInfo()).thenReturn("3. Checkout Book");
        when(option4.getName()).thenReturn("Return Book");
        when(option4.getId()).thenReturn(4);
        when(option4.getOptionInfo()).thenReturn("4. Return Book");
        when(option5.getName()).thenReturn("Logout");
        when(option5.getId()).thenReturn(5);
        when(option5.getOptionInfo()).thenReturn("5. Logout");

        menu = new Menu(options);

        menu.displayMenu(console);

        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Quit | 3. Checkout Book | 4. Return Book\n" +
                                                  "      5. Logout");
        inOrder.verify(console, times(1)).println("=====================================================================");
    }
}