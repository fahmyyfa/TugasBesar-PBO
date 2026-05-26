package org.example.demo.Service;

import java.time.LocalDate;

public interface NotificationService {
    void sendBorrowNotification(String email, String bookId, String title, LocalDate returnDate);
}