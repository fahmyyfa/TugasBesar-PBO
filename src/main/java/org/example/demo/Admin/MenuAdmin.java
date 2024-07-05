package org.example.demo.Admin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.demo.DarkLightMode;
import org.example.demo.LoginAdmin;

import static org.example.demo.LoginAdmin.bgAll;

public class MenuAdmin extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the AnchorPane
        AnchorPane root = new AnchorPane();

        ImageView backgroundViews = new ImageView(new Image(bgAll));
        backgroundViews.setPreserveRatio(false);

        root.getChildren().add(backgroundViews);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            backgroundViews.setFitWidth(newVal.doubleValue());
        });
        root.heightProperty().addListener((obs, oldVal, newVal) -> {
            backgroundViews.setFitHeight(newVal.doubleValue());
        });

        // Create the Labels
        Label titleLabel = new Label("Admin Menu");
        titleLabel.setFont(new Font("System Bold", 36));

        // Create the Buttons
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(new Font("System Bold", 14));
        logoutButton.setPrefSize(119, 40);

        logoutButton.setOnAction(actionEvent -> {
            LoginAdmin loginAdmin = new LoginAdmin();
            loginAdmin.start(primaryStage);
        });

        Button listStudentButton = new Button("List Student");
        listStudentButton.setFont(new Font("System Bold", 18));
        listStudentButton.setPrefSize(212, 76);

        listStudentButton.setOnAction(actionEvent -> {
            ListStudent listStudent = new ListStudent();
            listStudent.start(primaryStage);
        });

        Button listBookButton = new Button("List Book");
        listBookButton.setFont(new Font("System Bold", 18));
        listBookButton.setPrefSize(212, 76);

        listBookButton.setOnAction(actionEvent -> {
            ListBook listBook = new ListBook();
            try {
                listBook.start(primaryStage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Button addStudentButton = new Button("Add Student");
        addStudentButton.setFont(new Font("System Bold", 18));
        addStudentButton.setPrefSize(212, 76);

        addStudentButton.setOnAction(actionEvent -> {
            StudentAdd studentAdd = new StudentAdd();
            studentAdd.start(primaryStage);
        });

        Button addBookButton = new Button("Add Book");
        addBookButton.setFont(new Font("System Bold", 18));
        addBookButton.setPrefSize(212, 76);

        addBookButton.setOnAction(actionEvent -> {
            BookAdd bookAdd = new BookAdd();
            bookAdd.start(primaryStage);
        });

        // Create GridPane to organize buttons
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20); // Vertical gap between rows
        gridPane.setHgap(20); // Horizontal gap between columns

        // Add buttons to the GridPane
        gridPane.add(listStudentButton, 0, 0);
        gridPane.add(addStudentButton, 1, 0);
        gridPane.add(listBookButton, 0, 1);
        gridPane.add(addBookButton, 1, 1);

        // Create VBox to center the components
        VBox centerBox = new VBox(20, titleLabel, gridPane);
        centerBox.setAlignment(Pos.CENTER);

        // Add VBox to the center of the AnchorPane
        AnchorPane.setTopAnchor(centerBox, 0.0);
        AnchorPane.setBottomAnchor(centerBox, 0.0);
        AnchorPane.setLeftAnchor(centerBox, 0.0);
        AnchorPane.setRightAnchor(centerBox, 0.0);
        root.getChildren().add(centerBox);

        // Position the logout button at the bottom left
        root.getChildren().add(logoutButton);
        AnchorPane.setBottomAnchor(logoutButton, 20.0);
        AnchorPane.setLeftAnchor(logoutButton, 20.0);

        // Create the Scene and set it on the Stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Admin Menu");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        // Apply theme
        DarkLightMode.applyTheme(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
