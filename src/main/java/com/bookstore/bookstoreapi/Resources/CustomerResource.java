package com.bookstore.bookstoreapi.Resources;

import com.bookstore.bookstoreapi.Models.Customer;
import com.bookstore.bookstoreapi.Exceptions.CustomerNotFoundException;
import com.bookstore.bookstoreapi.Exceptions.InvalidInputException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    public static Map<Integer, Customer> customers = new HashMap<>();
    private static int currentId = 1;

    @POST
    public Response createCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()
         || customer.getEmail() == null || customer.getEmail().trim().isEmpty()
         || customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Customer name, email, and password cannot be empty.");
        }
        customer.setId(currentId++);
        customers.put(customer.getId(), customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") int id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return customer;
    }

    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        Customer existingCustomer = customers.get(id);
        if (existingCustomer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        if (updatedCustomer.getName() == null || updatedCustomer.getName().trim().isEmpty()
         || updatedCustomer.getEmail() == null || updatedCustomer.getEmail().trim().isEmpty()
         || updatedCustomer.getPassword() == null || updatedCustomer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Customer name, email, and password cannot be empty.");
        }

        updatedCustomer.setId(id);
        customers.put(id, updatedCustomer);
        return updatedCustomer;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        Customer removed = customers.remove(id);
        if (removed == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return Response.noContent().build();
    }
}
