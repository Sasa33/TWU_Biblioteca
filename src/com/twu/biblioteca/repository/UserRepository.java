package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;

import java.util.List;

import static java.util.Arrays.asList;

public class UserRepository {
    private List<User> repository;

    public UserRepository(List<User> users) {
        repository = users;
    }

    public UserRepository() {

    }

    public List<User> getAllUsers() {
        if (this.repository != null) {
            return this.repository;
        } else {
            User user1 = new User("user1", "000-0001", "123456", "user1@thoughtworks.com", "13000000000");
            User user2 = new User("user2", "000-0002", "654321", "user2@thoughtworks.com", "15000000000");

            List<User> users = asList(user1, user2);

            return users;
        }
    }
}
