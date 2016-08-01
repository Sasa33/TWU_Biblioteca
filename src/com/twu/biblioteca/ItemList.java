package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class ItemList<T> {
    private List<T> availableItems;
    private List<T> checkedOutItems;

    public ItemList(List<T> items) {
        this.availableItems = items;
        this.checkedOutItems = new ArrayList<T>();
    }


    public List<T> getAvailableItems() {
        return this.availableItems;
    }

    public boolean checkoutItem(int chosen) {
//        this.checkedOutItems.add(this.availableItems.get(chosen - 1));
//        this.availableItems.remove(chosen - 1);


        List<T> remainingItems = new ArrayList<T>();

        for (int i = 0; i < this.availableItems.size(); i++) {
            if (i != chosen - 1) {
                remainingItems.add(this.availableItems.get(i));
            } else {
                this.checkedOutItems.add(this.availableItems.get(i));
            }
        }

        this.availableItems = remainingItems;
        return true;
    }

    public boolean checkIfItemExits(int chosen) {
        return chosen > 0 && chosen <= this.availableItems.size();
    }

    public List<T> getCheckedOutItems() {
        return this.checkedOutItems;
    }

    public void returnItem(int index) {
//        this.availableItems.add(this.checkedOutItems.get(index - 1));
//
//        this.checkedOutItems.remove(index - 1);


        List<T> remainingCheckedOutItems = new ArrayList<T>();

        for (int i = 0; i < this.checkedOutItems.size(); i++) {
            if (i != index - 1) {
                remainingCheckedOutItems.add(this.checkedOutItems.get(i));
            } else {
                this.availableItems.add(this.checkedOutItems.get(i));
            }
        }

        this.checkedOutItems = remainingCheckedOutItems;
    }

    public boolean checkIfBookCanBeReturned(int chosen) {
        return chosen > 0 && chosen <= this.checkedOutItems.size();
    }
}
