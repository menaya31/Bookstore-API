/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.bookstoreapi.Resources;

/**
 *
 * @author User
 */

import com.bookstore.bookstoreapi.Models.Order;
import com.bookstore.bookstoreapi.Models.Cart;
import com.bookstore.bookstoreapi.Exceptions.OrderNotFoundException;
import com.bookstore.bookstoreapi.Exceptions.CartNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    public static Map<Integer, List<Order>> customerOrders = new HashMap<>();
    public static Map<Integer, Cart> customerCarts = CartResource.customerCarts;
    private static int currentOrderId = 1;

    @POST
    public Order createOrder(@PathParam("customerId") int customerId) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }
        Order newOrder = new Order(currentOrderId++, customerId, new ArrayList<>(cart.getItems()));
        customerOrders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(newOrder);
        customerCarts.remove(customerId);
        return newOrder;
    }

    @GET
    public List<Order> getAllOrders(@PathParam("customerId") int customerId) {
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    }

    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("customerId") int customerId,
                              @PathParam("orderId") int orderId) {
        List<Order> orders = customerOrders.get(customerId);
        if (orders == null) {
            throw new OrderNotFoundException("No orders found for customer ID " + customerId);
        }
        return orders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + orderId + " not found."));
    }
}
