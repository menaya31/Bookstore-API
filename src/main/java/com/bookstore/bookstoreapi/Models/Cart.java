package com.bookstore.bookstoreapi.Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int customerId;
    public List<CartItem> items = new ArrayList<>();

    public Cart() {}

   
    public Cart(int customerId) {
        this.customerId = customerId;
    }

    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(int bookId) {
        items.removeIf(item -> item.getBookId() == bookId);
    }
}
