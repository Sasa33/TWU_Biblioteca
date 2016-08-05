package com.twu.biblioteca.entity;

public interface Loanable {
    public void checkedout(User user);

    public void checkedIn();

    public boolean isCheckedOut();

    public String getOwner();
}
