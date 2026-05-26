package org.example.demo.Database;

public class StubBookProvider {

    public Book getBook() {
        return new Book("1234-5678-9012", "Pemrograman Java", "James Gosling", "Text", 10);
    }

    public Book getEmptyStockBook() {
        return new Book("0000-0000-0000", "Buku Habis", "Unknown", "Story", 0);
    }

    public Book getHighStockBook() {
        return new Book("9999-9999-9999", "Buku Populer", "Best Author", "History", 999);
    }
}
