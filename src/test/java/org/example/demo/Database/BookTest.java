package org.example.demo.Database;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private StubBookProvider stubProvider;
    private Book book;

    @BeforeEach
    void setUp() {
        stubProvider = new StubBookProvider();
    }

    @AfterEach
    void tearDown() {
        stubProvider = null;
        book = null;
    }

    @Test
    void testConstructor() {
        book = stubProvider.getBook();

        assertEquals("1234-5678-9012", book.getId_buku());
        assertEquals("Pemrograman Java", book.getTitle());
        assertEquals("James Gosling", book.getAuthor());
        assertEquals("Text", book.getCategory());
        assertEquals(10, book.getStock());
    }

    @Test
    void testSetTitle() {
        book = stubProvider.getBook();
        book.setTitle("Algoritma dan Struktur Data");
        assertEquals("Algoritma dan Struktur Data", book.getTitle());
    }

    @Test
    void testSetAuthor() {
        book = stubProvider.getBook();
        book.setAuthor("Robert Sedgewick");
        assertEquals("Robert Sedgewick", book.getAuthor());
    }

    @Test
    void testSetCategory() {
        book = stubProvider.getBook();
        book.setCategory("History");
        assertEquals("History", book.getCategory());
    }

    @Test
    void testSetIdBuku() {
        book = stubProvider.getBook();
        book.setId_buku("AAAA-BBBB-CCCC");
        assertEquals("AAAA-BBBB-CCCC", book.getId_buku());
    }

    @Test
    void testSetStock() {
        book = stubProvider.getBook();
        book.setStock(25);
        assertEquals(25, book.getStock());
    }

    @Test
    void testStockDecreaseAfterBorrow() {
        book = stubProvider.getBook();
        int stockAwal = book.getStock();
        book.setStock(stockAwal - 1);
        assertEquals(9, book.getStock());
    }

    @Test
    void testStockIncreaseAfterReturn() {
        book = stubProvider.getBook();
        int stockAwal = book.getStock();
        book.setStock(stockAwal + 1);
        assertEquals(11, book.getStock());
    }

    @Test
    void testEmptyStockFromStub() {
        book = stubProvider.getEmptyStockBook();
        assertEquals(0, book.getStock());
        assertEquals("Buku Habis", book.getTitle());
    }

    @Test
    void testHighStockFromStub() {
        book = stubProvider.getHighStockBook();
        assertEquals(999, book.getStock());
        assertEquals("Buku Populer", book.getTitle());
    }

    @Test
    void testSetDuration() {
        book = stubProvider.getBook();
        book.setDuration(7);
        assertEquals(7, book.getDuration());
    }

    @Test
    void testDefaultDuration() {
        book = stubProvider.getBook();
        assertEquals(0, book.getDuration());
    }
}
