package com.twu.biblioteca.control;

import com.twu.biblioteca.entity.Loanable;
import com.twu.biblioteca.entity.User;
import com.twu.biblioteca.repository.LoanableItemRepository;

import java.util.List;

public class LoanableItemManagerImpl<T extends Loanable> implements LoanableItemManager<T> {
    private LoanableItemRepository<T> itemRepository;

    public LoanableItemManagerImpl(LoanableItemRepository<T> itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<T> getAvailableItems() {
        return itemRepository.getAvailableItems();
    }

    public boolean checkoutItem(int chosen, User user) {
        List<T> availableItems = this.getAvailableItems();

        for (int i = 0; i < availableItems.size(); i++) {
            if (i == chosen - 1) {
                availableItems.get(i).checkedout(user);
            }
        }

        return true;
    }

    public boolean checkIfItemExits(int chosen) {
        return chosen > 0 && chosen <= this.getAvailableItems().size();
    }

    public List<T> getCheckedOutItems() {
        return itemRepository.getCheckedOutItems();
    }

    public void returnItem(int chosen) {
        List<T> checkedOutBooks = itemRepository.getCheckedOutItems();

        for (int i = 0; i < checkedOutBooks.size(); i++) {
            if (i == chosen - 1) {
                checkedOutBooks.get(i).checkedIn();
            }
        }
    }

    public boolean checkIfItemCanBeReturned(int chosen) {
        return chosen > 0 && chosen <= this.getCheckedOutItems().size();
    }

    public boolean isAnyItemCanBeReturned() {
        return !this.getCheckedOutItems().isEmpty();
    }

    public boolean isAnyItemCanBeCheckedout() {
        return !this.getAvailableItems().isEmpty();
    }
}
