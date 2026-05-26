package org.example.demo;

import org.example.demo.Database.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginStudentTest {

    private LoginStudent loginStudent;

    @BeforeEach
    public void setUp() {
        loginStudent = new LoginStudent();
        User.students.clear();
        User.students.add(new org.example.demo.Database.Student("12345", "12345", "NamaDummy", "FakultasDummy"));
    }

    @Test
    public void testLoginSuccess() {
        String result = loginStudent.validateLogin("12345", "12345");
        assertEquals("SUCCESS", result);
    }

    @Test
    public void testLoginIncorrectPassword() {
        String result = loginStudent.validateLogin("12345", "salahpassword");
        assertEquals("Incorrect password.", result);
    }

    @Test
    public void testLoginNimNotFound() {
        String result = loginStudent.validateLogin("99999", "12345");
        assertEquals("NIM not found.", result);
    }

    @Test
    public void testLoginEmptyNim() {
        String result = loginStudent.validateLogin("", "12345");
        assertEquals("NIM empty.", result);
    }

    @Test
    public void testLoginEmptyPassword() {
        String result = loginStudent.validateLogin("12345", "");
        assertEquals("Password empty.", result);
    }
}