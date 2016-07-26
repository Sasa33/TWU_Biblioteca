package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class OptionTest {
    private Option option;

    @Test
    public void should_initialize_an_option_with_id_and_name_successfully() {
        String optionName = "List Books";
        int optionId = 1;

        option = new Option(optionId, optionName);

        assertEquals(optionName, option.getName());
        assertEquals(optionId, option.getId());
    }

    @Test
    public void should_call_listAvailableBooks_method_when_List_Books_option_is_chosen() {
        BibliotecaApp app = mock(BibliotecaApp.class);

        int optionId = 1;
        String optionName = "List Books";
        option = new Option(optionId, optionName);

        option.execute(app);

        verify(app, times(1)).listAvailableBooks();
    }
}