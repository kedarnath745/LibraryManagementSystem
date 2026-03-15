package com.example.library_management_system;

import javafx.scene.image.Image;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private Image image;

    public Book(String title, String author, String isbn, boolean isAvailable, String imagePath) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;

        // Check if the image path is empty or invalid
        if (imagePath == null || imagePath.isEmpty()) {
            // Load a default image if the path is empty
            this.image = new Image("file:src/main/java/BookImages/Untitled.jpg");
        } else {
            try {
                this.image = new Image(imagePath); // Attempt to load the provided image
            } catch (Exception e) {
                // Load the default image if the provided path is invalid
                this.image = new Image("file:src/main/java/BookImages/Untitled.jpg");
            }
        }
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public Image getImage() { return image; }
}
