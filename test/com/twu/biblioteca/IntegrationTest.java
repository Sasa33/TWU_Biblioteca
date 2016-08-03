package com.twu.biblioteca;

import com.twu.biblioteca.option.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class IntegrationTest {
    private BibliotecaApp app;
    private Console console;
    private InOrder inOrder;

    @Before
    public void setUpStreams() {
        console = mock(Console.class);
        inOrder = inOrder(console);

        Option option1 = new ListBooksOption(1, "List Books");
        Option option2 = new CheckoutBookOption(2, "Checkout Book");
        Option option3 = new ReturnBookOption(3, "Return Book");
        Option option4 = new QuitOption(4, "Quit");
        Menu menu = new Menu(asList(option1, option2, option3, option4));

        app = new BibliotecaApp(console, menu);
    }

    @Test
    public void should_test_the_whole_functionality_of_this_app() {
        when(console.getNextInt()).thenReturn(1, 2, 1, 3, 2, 1, 1, 8);

        String expectedNumber = "000-0001";
        String expectedPassword = "123456";
        when(console.getNextString()).thenReturn(expectedNumber, expectedPassword);


        app.startWithMenu();

        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Checkout Book | 3. Return Book | 4. Quit");
        inOrder.verify(console, times(1)).println("=====================================================================");

        // show book list:
        inOrder.verify(console, times(1)).println("Book List:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");
        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Checkout Book | 3. Return Book | 4. Quit");
        inOrder.verify(console, times(1)).println("=====================================================================");

        // login before checkout
        inOrder.verify(console, times(1)).println("Please login first...");
        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("Login successful!");

        // check out an book
        inOrder.verify(console, times(1)).println("Which book do you want to checkout:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");

        // checkout successfully
        inOrder.verify(console, times(1)).println("Thank you! Enjoy the book!");

        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Checkout Book | 3. Return Book | 4. User Info\n" +
                "      5. List Movies | 6. Checkout Movie | 7. Return Movie | 8. Quit\n" +
                        "      9. Logout");
        inOrder.verify(console, times(1)).println("=====================================================================");


        // return a book
        inOrder.verify(console, times(1)).println("Which book do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");

        // return an invalid book
        inOrder.verify(console, times(1)).println("That is not a valid book to return. Please choose again!");
        inOrder.verify(console, times(1)).println("Which book do you want to return:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");

        // return a valid book
        inOrder.verify(console, times(1)).println("Thank you for returning the book!");

        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Checkout Book | 3. Return Book | 4. User Info\n" +
                "      5. List Movies | 6. Checkout Movie | 7. Return Movie | 8. Quit\n" +
                        "      9. Logout");
        inOrder.verify(console, times(1)).println("=====================================================================");


        // show book list again
        inOrder.verify(console, times(1)).println("Book List:");
        inOrder.verify(console, times(1)).println("\t1. Head First Java | Kathy Sierra & Bert Bates | 2003");
        inOrder.verify(console, times(1)).println("\t2. Refactoring | Martin Fowler | 1999");

        // exit the app
        inOrder.verify(console, times(1)).println("Thank you for coming to the Bangalore Public Library! See you next time.");
    }

    @Test
    public void integration_test_for_login() {
        Option option1 = new LoginOption(1, "Login");
        Option option2 = new QuitOption(2, "Quit");
        Menu menu = new Menu(asList(option1, option2));

        app = new BibliotecaApp(console, menu);

        when(console.getNextInt()).thenReturn(1, 4, 8);
        String expectedNumber = "000-0001";
        String expectedPassword = "123456";
        when(console.getNextString()).thenReturn(expectedNumber, expectedPassword);

        app.startWithMenu();

        inOrder.verify(console, times(1)).println("Welcome to the Bangalore Public Library!");
        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. Login | 2. Quit");
        inOrder.verify(console, times(1)).println("=====================================================================");

        inOrder.verify(console, times(1)).print("Library Number: ");
        inOrder.verify(console, times(1)).print("Password: ");

        inOrder.verify(console, times(1)).println("Login successful!");

        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Checkout Book | 3. Return Book | 4. User Info\n" +
                "      5. List Movies | 6. Checkout Movie | 7. Return Movie | 8. Quit\n" +
                        "      9. Logout");
        inOrder.verify(console, times(1)).println("=====================================================================");

        inOrder.verify(console, times(1)).println("Name: user1");
        inOrder.verify(console, times(1)).println("Email Address: user1@thoughtworks.com");
        inOrder.verify(console, times(1)).println("Phone: 13000000000");

        inOrder.verify(console, times(1)).println("=====================================================================");
        inOrder.verify(console, times(1)).println("Please select an option from menu below.");
        inOrder.verify(console, times(1)).println("Menu: 1. List Books | 2. Checkout Book | 3. Return Book | 4. User Info\n" +
                "      5. List Movies | 6. Checkout Movie | 7. Return Movie | 8. Quit\n" +
                        "      9. Logout");
        inOrder.verify(console, times(1)).println("=====================================================================");

        // exit the app
        inOrder.verify(console, times(1)).println("Thank you for coming to the Bangalore Public Library! See you next time.");
    }
}
