package org.example.demo;

import org.example.demo.Database.Book;
import org.example.demo.Database.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testLoginBerhasil() {

        boolean result = User.login(
                "student@example.com",
                "password123"
        );

        assertTrue(result);

    }

    @Test
    public void testLoginGagal() {

        boolean result = User.login(
                "student@example.com",
                "salah"
        );

        assertFalse(result);

    }

    @Test
    public void testTambahBukuKeArray() {

        Book book = new Book(
                "BK001",
                "Java Programming",
                "Arvin",
                "Programming",
                10
        );

        User.addBookToArray(book, 0);

        assertEquals(
                "Java Programming",
                User.bookArray[0].getTitle()
        );

    }

    @Test
    public void testTambahBukuIndexTidakValid() {

        Book book = new Book(
                "BK002",
                "Database",
                "Budi",
                "Education",
                5
        );

        User.addBookToArray(book, 200);

        assertNull(
                User.bookArray[99]
        );

    }

}