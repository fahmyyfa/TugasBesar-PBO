module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.sql;
    requires sqlite.jdbc;
    requires org.apache.pdfbox;
    requires activation;
    requires transitive javafx.graphics;


    opens org.example.demo to javafx.fxml;
    opens org.example.demo.Database to javafx.base;
    opens org.example.demo.Domain to javafx.base, javafx.fxml;
    opens org.example.demo.Service to javafx.base, javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.Admin;
    exports org.example.demo.Student;
    exports org.example.demo.Main to javafx.graphics;
    exports org.example.demo.Time to javafx.graphics;



}
