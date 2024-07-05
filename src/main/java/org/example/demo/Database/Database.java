package org.example.demo.Database;


import java.sql.*;

public class Database {
    public static final String user_database = "jdbc:sqlite:src/main/java/org/example/demo/Database/user_database.db";
    public static final String book_database = "jdbc:sqlite:src/main/java/org/example/demo/Database/Book.db";

    public static Connection connect(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }

    //=========================================== Method For Student ===================================================
    //Untuk memasukkan isi dari program ke database
    public static void student_addStudent(String name, String nim, String faculty, String program) {

        String sql = "INSERT INTO user_Mahasiswa (name, nim, faculty, program) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(user_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, nim);
            pstmt.setString(3, faculty);
            pstmt.setString(4, program);

            pstmt.executeUpdate();

            System.out.println("data mahasiswa berhasil ditambahkan ke database.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan data mahasiswa ke database: " + e.getMessage());
        }
    }

    //Untuk memindahkan isi dari database ke arraylist
    public static void student_database() {
        String sql = "SELECT * FROM user_Mahasiswa";

        try (Connection conn = connect(user_database);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String nim = rs.getString("nim");
                String faculty = rs.getString("faculty");
                String program = rs.getString("program");

                User.students.add(new Student(name, nim, faculty, program));
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data mahasiswa: " + e.getMessage());
        }
    }


    //============================================ Method For Book ====================================================

    public static void book_addBook(String id, String title, String author, String category, int stock) {

        String sql = "INSERT INTO Book_data (id, title, author, category, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect(book_database);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setString(4, category);
            pstmt.setInt(5, stock);


            pstmt.executeUpdate();

            System.out.println("Berhasil Tambah buku kedalam database");
        } catch (SQLException e) {
            System.out.println("Error = Database.book_addBook; More info : " + e.getMessage());
        }
    }

    public static void book_bookDatabase() {
        String sql = "SELECT * FROM book_data";

        try (Connection conn = connect(book_database);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id       = rs.getString("id");
                String title    = rs.getString("title");
                String author   = rs.getString("author");
                String category = rs.getString("category");
                int stock       = rs.getInt("stock");


                User.books.add(new Book(id, title, author, category, stock));
            }

        } catch (SQLException e) {
            System.out.println("Gagal menampilkan data mahasiswa: " + e.getMessage());
        }
    }
}
