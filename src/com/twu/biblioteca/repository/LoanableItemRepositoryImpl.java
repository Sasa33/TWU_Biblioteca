package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Loanable;

import java.util.ArrayList;
import java.util.List;

public class LoanableItemRepositoryImpl<T extends Loanable> implements LoanableItemRepository<T> {
    private List<T> LoanableItems;

    public LoanableItemRepositoryImpl(List<T> loanableItems) {
        this.LoanableItems = loanableItems;
    }

    public List<T> getAllItems() {
        return this.LoanableItems;
    }

    public List<T> getAvailableItems() {
        List<T> availableItems = new ArrayList<T>();

        for (T item : LoanableItems) {
            if (!item.isCheckedOut()) {
                availableItems.add(item);
            }
        }

        return availableItems;
    }

    public List<T> getCheckedOutItems() {
        List<T> checkedOutItems  = new ArrayList<T>();

        for (T movie : LoanableItems) {
            if (movie.isCheckedOut()) {
                checkedOutItems.add(movie);
            }
        }

        return checkedOutItems;
    }

    public List<T> findItemsByOwnerNumber(String userNumber) {
        List<T> result = new ArrayList<T>();

        for (T item : this.LoanableItems) {
            String owner = item.getOwner();
            if (owner.equals(userNumber)) {
                result.add(item);
            }
        }

        return result;
    }
}
