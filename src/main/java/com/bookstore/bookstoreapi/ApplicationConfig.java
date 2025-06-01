/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.bookstoreapi;

/**
 *
 * @author User
 */
/*
import com.bookstore.bookstoreapi.ExceptionMappers.CustomerNotFoundExceptionMapper;
import com.bookstore.bookstoreapi.ExceptionMappers.BookNotFoundExceptionMapper;
import com.bookstore.bookstoreapi.ExceptionMappers.CartNotFoundExceptionMapper;
import com.bookstore.bookstoreapi.ExceptionMappers.GlobalExceptionMapper;
import com.bookstore.bookstoreapi.ExceptionMappers.InvalidInputExceptionMapper;
import com.bookstore.bookstoreapi.ExceptionMappers.OrderNotFoundExceptionMapper;
import com.bookstore.bookstoreapi.ExceptionMappers.AuthorNotFoundExceptionMapper;
import com.bookstore.bookstoreapi.Resources.AuthorResource;
import com.bookstore.bookstoreapi.Resources.BookResource;
import com.bookstore.bookstoreapi.Resources.CartResource;
import com.bookstore.bookstoreapi.Resources.CustomerResource;
import com.bookstore.bookstoreapi.Resources.OrderResource;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // Register Resource classes
        resources.add(AuthorResource.class);
        resources.add(BookResource.class);
        resources.add(CartResource.class);
        resources.add(CustomerResource.class);
        resources.add(OrderResource.class);

        // Register Exception Mappers
        resources.add(AuthorNotFoundExceptionMapper.class);
        resources.add(BookNotFoundExceptionMapper.class);
        resources.add(CustomerNotFoundExceptionMapper.class);
        resources.add(CartNotFoundExceptionMapper.class);
        resources.add(OrderNotFoundExceptionMapper.class);
        resources.add(InvalidInputExceptionMapper.class);
        resources.add(GlobalExceptionMapper.class);

        return resources;
    }
}
*/