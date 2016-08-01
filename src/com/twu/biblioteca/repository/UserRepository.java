package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.User;

import java.util.List;

public class UserRepository {
    private List<User> repository;

    public UserRepository(List<User> users) {
        repository = users;
    }

    public List<User> getAllUsers() {
        return this.repository;
    }
}
