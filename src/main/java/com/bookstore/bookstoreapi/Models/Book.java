/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookstore.bookstoreapi.Models;

/**
 *
 * @author User
 */

public class Book {
    private int id;
    private int authorId;
    private String title;
    private String isbn;
    private int publicationYear;
    private double price;
    private int stock;

    public Book() {}

    public Book(int id, int authorId, String title, String isbn, int publicationYear, double price, int stock) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public int getAuthorId() { return authorId; }
    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setId(int id) { this.id = id; }
    public void setAuthorId(int authorId) { this.authorId = authorId; }
    public void setTitle(String title) { this.title = title; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}
