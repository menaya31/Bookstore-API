/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.bookstoreapi.Models;

/**
 *
 * @author User
 */

import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private List<CartItem> items;

    public Order() {}

    public Order(int id, int customerId, List<CartItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public List<CartItem> getItems() { return items; }

    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
