package com.twu.biblioteca.entity;

public class User {

    private String name;
    private String number;
    private String password;
    private String email;
    private String phone;

    public User(String name, String number, String password, String email, String phone) {
        this.name = name;
        this.number = number;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getUserInfo() {
        return getName() + " | " + getEmail() + " | " + getPhone();
    }
}
