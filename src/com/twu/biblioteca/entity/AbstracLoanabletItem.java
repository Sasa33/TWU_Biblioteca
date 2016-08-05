package com.twu.biblioteca.entity;

public class AbstracLoanabletItem implements Loanable{
    private boolean checkedOut;
    private String owner;

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void checkedout(User user) {
        setCheckedOut(true);
        setOwner(user.getNumber());
    }

    public void checkedIn() {
        setCheckedOut(false);
        setOwner("");
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public String getOwner() {
        return owner;
    }
}
