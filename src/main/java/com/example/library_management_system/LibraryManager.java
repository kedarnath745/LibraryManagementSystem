package com.example.library_management_system;

import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Book> bookInventory = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public LibraryManager() {
        // Initialize with some users
        users.add(new User("admin", "123"));
        users.add(new User("test", "test"));

        // Initialize with some books with image paths
        bookInventory.add(new Book("NARUTO", "Masashi Kishimoto", "978-3-16-148410-0", true, "file:src/main/java/BookImages/Naruto.png"));
        bookInventory.add(new Book("One Piece", "Eichiro Oda", "978-0-262-13472-9", true, "file:src/main/java/BookImages/Onepiece.png"));
        bookInventory.add(new Book("Berserk", "Kentaro Miura", "978-1-56619-909-4", true, "file:src/main/java/BookImages/berserk.png"));
        bookInventory.add(new Book("Psychology Of Money", "Morgan Housel", "978-0-19-852663-6", true, "file:src/main/java/BookImages/PsychologyOfMoney.jpg"));
        bookInventory.add(new Book("Atomic Habits", "James Clear", "978-0-07-000484-9", true, "file:src/main/java/BookImages/Atomic-Habits-front-cover.png"));
        bookInventory.add(new Book("Effective Java", "Joshua Bloch", "978-0-393-97950-3", true, "file:src/main/java/BookImages/EffectiveJava.jpg"));
        bookInventory.add(new Book("Java Cook Book", "Ian F.Darwin", "978-0-14-044913-6", true, "file:src/main/java/BookImages/JavaCookBook.jpg"));

    }

    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getBookInventory() {
        return bookInventory;
    }

    public void addBook(Book book) {
        bookInventory.add(book);
    }

    public void deleteBook(Book book) {
        bookInventory.remove(book);
    }
}
