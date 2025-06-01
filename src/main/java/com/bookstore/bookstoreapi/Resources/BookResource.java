package com.bookstore.bookstoreapi.Resources;

import com.bookstore.bookstoreapi.Models.Book;
import com.bookstore.bookstoreapi.Exceptions.BookNotFoundException;
import com.bookstore.bookstoreapi.Exceptions.InvalidInputException;
import com.bookstore.bookstoreapi.Exceptions.AuthorNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private static Map<Integer, Book> books = new HashMap<>();
    private static int currentId = 1;

    @POST
    public Response createBook(Book book) {
        validateBook(book);

        
        if (!AuthorResource.authors.containsKey(book.getAuthorId())) {
            throw new AuthorNotFoundException("Author with ID " + book.getAuthorId() + " not found.");
        }

        book.setId(currentId++);
        books.put(book.getId(), book);
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @GET
    public Collection<Book> getAllBooks() {
        return books.values();
    }

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") int id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return book;
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book updatedBook) {
        Book existingBook = books.get(id);
        if (existingBook == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }

        validateBook(updatedBook);

        
        if (!AuthorResource.authors.containsKey(updatedBook.getAuthorId())) {
            throw new AuthorNotFoundException("Author with ID " + updatedBook.getAuthorId() + " not found.");
        }

        updatedBook.setId(id);
        books.put(id, updatedBook);
        return updatedBook;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book removed = books.remove(id);
        if (removed == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return Response.noContent().build();
    }

    
    private void validateBook(Book book) {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Book title cannot be empty.");
        }
        if (book.getAuthorId() <= 0) {
            throw new InvalidInputException("Author ID must be a positive number.");
        }
        if (book.getPublicationYear() > 2025) {
            throw new InvalidInputException("Publication year cannot be in the future.");
        }
        if (book.getPrice() < 0) {
            throw new InvalidInputException("Price cannot be negative.");
        }
        if (book.getStock() < 0) {
            throw new InvalidInputException("Stock cannot be negative.");
        }
    }
}
