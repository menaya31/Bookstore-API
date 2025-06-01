/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.bookstoreapi.Resources;

/**
 *
 * @author User
 */

import com.bookstore.bookstoreapi.Models.Author;
import com.bookstore.bookstoreapi.Exceptions.AuthorNotFoundException;
import com.bookstore.bookstoreapi.Exceptions.InvalidInputException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    public static Map<Integer, Author> authors = new HashMap<>();
    private static int currentId = 1;

    @POST
    public Response createAuthor(Author author) {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty.");
        }
        author.setId(currentId++);
        authors.put(author.getId(), author);
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public Collection<Author> getAllAuthors() {
        return authors.values();
    }

    @GET
    @Path("/{id}")
    public Author getAuthor(@PathParam("id") int id) {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return author;
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        Author existingAuthor = authors.get(id);
        if (existingAuthor == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        if (updatedAuthor.getName() == null || updatedAuthor.getName().trim().isEmpty()) {
            throw new InvalidInputException("Author name cannot be empty.");
        }
        updatedAuthor.setId(id);
        authors.put(id, updatedAuthor);
        return updatedAuthor;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        Author removed = authors.remove(id);
        if (removed == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return Response.noContent().build();
    }
}
