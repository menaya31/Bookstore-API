package com.bookstore.bookstoreapi.Resources;

import com.bookstore.bookstoreapi.Models.Cart;
import com.bookstore.bookstoreapi.Models.CartItem;
import com.bookstore.bookstoreapi.Exceptions.CartNotFoundException;
import com.bookstore.bookstoreapi.Exceptions.CustomerNotFoundException;
import com.bookstore.bookstoreapi.Exceptions.InvalidInputException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    public static Map<Integer, Cart> customerCarts = new HashMap<>();

    
    @POST
    public Response createCart(@PathParam("customerId") int customerId) {
        if (!CustomerResource.customers.containsKey(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }

        if (customerCarts.containsKey(customerId)) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Cart already exists for customer ID " + customerId).build();
        }

        Cart cart = new Cart(customerId);
        customerCarts.put(customerId, cart);
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

    
    @GET
    public Cart viewCart(@PathParam("customerId") int customerId) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }
        return cart;
    }

  
    @POST
    @Path("/items")
    public Cart addItemToCart(@PathParam("customerId") int customerId, CartItem item) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }

        if (item.getQuantity() <= 0) {
            throw new InvalidInputException("Quantity must be greater than zero.");
        }

        cart.addItem(item);
        return cart;
    }

    
    @PUT
    @Path("/items/{bookId}")
    public Cart updateItemQuantity(@PathParam("customerId") int customerId,
                                   @PathParam("bookId") int bookId,
                                   CartItem updatedItem) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }

        cart.removeItem(bookId);
        cart.addItem(updatedItem);
        return cart;
    }

    
    @DELETE
    @Path("/items/{bookId}")
    public Cart removeItemFromCart(@PathParam("customerId") int customerId,
                                   @PathParam("bookId") int bookId) {
        Cart cart = customerCarts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }

        cart.removeItem(bookId);
        return cart;
    }
}