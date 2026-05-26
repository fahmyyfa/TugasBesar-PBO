package org.example.demo.Domain;

import org.example.demo.Database.Book;
import org.example.demo.Service.NotificationService;
import java.time.LocalDate;

public class BorrowManager {
    private final NotificationService notificationService;

    public BorrowManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public boolean processBorrow(Book book, String studentEmail) {
        if (book == null || book.getStock() <= 0) {
            return false;
        }
        
        book.setStock(book.getStock() - 1);
        book.setDuration(7);
        LocalDate returnDate = LocalDate.now().plusDays(7);
        
        notificationService.sendBorrowNotification(studentEmail, book.getId_buku(), book.getTitle(), returnDate);
        return true;
    }
}