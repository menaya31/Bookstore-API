/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.bookstoreapi.ExceptionMappers;

/**
 *
 * @author User
 */
import com.bookstore.bookstoreapi.Exceptions.OrderNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class OrderNotFoundExceptionMapper implements ExceptionMapper<OrderNotFoundException> {

    @Override
    public Response toResponse(OrderNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Order Not Found");
        error.put("message", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
