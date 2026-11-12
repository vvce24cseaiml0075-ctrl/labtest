package com.example;

import java.util.HashMap;
import java.util.Map;

public class LibraryManager {
    private Map<String, book> books = new HashMap<>();

    // 1. Add a book with a unique book ID and title
    public void addBook(String bookId, String title) {
        if (bookId == null || bookId.trim().isEmpty() || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID and title cannot be empty.");
        }
        if (books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book ID already exists: " + bookId);
        }
        books.put(bookId, new book(bookId, title));
    }

    // 2. Search for a book using its ID
    public book searchBook(String bookId) {
        return books.get(bookId);
    }

    // 3. Issue an available book
    public boolean issueBook(String bookId) {
        book book = books.get(bookId);
        if (book == null || book.isIssued()) {
            return false;
        }
        book.setIssued(true);
        return true;
    }

    // 4. Return an issued book
    public boolean returnBook(String bookId) {
        book book = books.get(bookId);
        if (book == null || !book.isIssued()) {
            return false;
        }
        book.setIssued(false);
        return true;
    }

    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        library.addBook("B101", "Clean Code");
        System.out.println("Added Book: " + library.searchBook("B101").getTitle());
    }
}