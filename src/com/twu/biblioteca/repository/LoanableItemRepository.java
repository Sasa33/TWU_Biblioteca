package com.twu.biblioteca.repository;

import com.twu.biblioteca.entity.Loanable;

import java.util.List;

public interface LoanableItemRepository<T extends Loanable> {
    public List<T> getAllItems();

    public List<T> getAvailableItems();

    public List<T> getCheckedOutItems();

    public List<T> findItemsByOwnerNumber(String userNumber);
}
