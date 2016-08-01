package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {
    private UserRepository repository;

    @Test
    public void should_get_all_users_correctly() {
        User user1 = mock(User.class);
        User user2 = mock(User.class);

        when(user1.getName()).thenReturn("user1");
        when(user2.getName()).thenReturn("user2");

        repository = new UserRepository(asList(user1, user2));

        List<User> users = repository.getAllUsers();


        assertEquals(users.size(), 2);
        assertEquals(users.get(0).getName(), "user1");
        assertEquals(users.get(1).getName(), "user2");
    }
}
