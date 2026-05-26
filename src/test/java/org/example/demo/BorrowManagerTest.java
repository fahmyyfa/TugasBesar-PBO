package org.example.demo;

import org.example.demo.Database.Book;
import org.example.demo.Database.User;
import org.example.demo.Database.Database;
import org.example.demo.Domain.BorrowManager;
import org.example.demo.Service.NotificationService;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowManagerTest {

    //fake
    private static class FakeNotificationService implements NotificationService {
        public boolean isNotificationSent = false;
        public LocalDate capturedReturnDate = null;

        @Override
        public void sendBorrowNotification(String email, String bookId, String title, LocalDate returnDate) {
            this.isNotificationSent = true;
            this.capturedReturnDate = returnDate; 
        }
    }

    @Test
    public void testProcessBorrow_Success_ShouldReduceStockAndCalculate7DaysReturn() {
        
        //1. setup (pengisian kondisi fake)
        User.books.clear();
        Database.book_bookDatabase(); 

        FakeNotificationService fakeNotification = new FakeNotificationService();
        BorrowManager borrowManager = new BorrowManager(fakeNotification);
        
        Book targetBook = User.books.get(0);
        targetBook.setStock(5);

        int expectedStock = 4; 
        LocalDate expectedReturnDate = LocalDate.now().plusDays(7); 

        //2. exercise (pemanggilan method pengujian)
        boolean result = borrowManager.processBorrow(targetBook, "student@univ.ac.id");

        //3. verify (pengecekan dan pembuktian hasil)
        assertTrue(result, "Proses peminjaman harusnya berhasil (true)");
        assertEquals(expectedStock, targetBook.getStock(), "Stok buku harus berkurang 1");
        assertEquals(7, targetBook.getDuration(), "Durasi peminjaman harus diset 7 hari");
        
        assertTrue(fakeNotification.isNotificationSent, "Notifikasi email harusnya terpicu");
        assertEquals(expectedReturnDate, fakeNotification.capturedReturnDate, "Tanggal pengembalian harus tepat 7 hari dari sekarang");

        //4. teardown (pembersihan, tpi tdk digunakan karna pengujian di lokal)
    }
}