package com.twu.biblioteca.control;

import com.twu.biblioteca.entity.Loanable;
import com.twu.biblioteca.entity.User;

import java.util.List;

public interface LoanableItemManager<T extends Loanable> {
    List<T> getAvailableItems();

    boolean checkIfItemExits(int chosen);

    boolean checkoutItem(int chosen, User user);

    List<T> getCheckedOutItems();

    boolean checkIfItemCanBeReturned(int chosen);

    void returnItem(int chosen);

    boolean isAnyItemCanBeReturned();

    boolean isAnyItemCanBeCheckedout();
}
