package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagerTest {
    private LibraryManager library;

    @BeforeEach
    void setUp() {
        library = new LibraryManager();
        library.addBook("B101", "Effective Java");
    }

    // 1. Adding and searching for a book
    @Test
    void testAddAndSearchBook() {
        library.addBook("B102", "DevOps Handbook");
        book found = library.searchBook("B102");
        assertNotNull(found);
        assertEquals("DevOps Handbook", found.getTitle());
        assertFalse(found.isIssued());
    }

    // 2. Handling duplicate book IDs
    @Test
    void testDuplicateBookIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            library.addBook("B101", "Duplicate Book");
        });
    }

    // 3. Issuing an available or already-issued book
    @Test
    void testIssueAvailableAndAlreadyIssuedBook() {
        assertTrue(library.issueBook("B101"));
        assertTrue(library.searchBook("B101").isIssued());

        // Issuing an already-issued book should return false
        assertFalse(library.issueBook("B101"));
    }

    // 4. Returning a book and updating its availability
    @Test
    void testReturnBookAndUpdateAvailability() {
        library.issueBook("B101");
        assertTrue(library.returnBook("B101"));
        assertFalse(library.searchBook("B101").isIssued());

        // Returning an unissued book should return false
        assertFalse(library.returnBook("B101"));
    }
}