module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.sql;
    requires sqlite.jdbc;
    requires org.apache.pdfbox;
    requires activation;


    opens org.example.demo to javafx.fxml;
    opens org.example.demo.Database to javafx.base;
    exports org.example.demo;
    exports org.example.demo.Admin;
    exports org.example.demo.Student;
    exports org.example.demo.Main to javafx.graphics;
    exports org.example.demo.Time to javafx.graphics;



}
