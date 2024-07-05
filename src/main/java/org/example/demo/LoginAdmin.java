package org.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.example.demo.Admin.MenuAdmin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LoginAdmin extends Application {

    private int attempts = 0; // Jumlah percobaan
    private LocalDateTime lastAttemptTime; // Waktu terakhir kali percobaan
    public static final String bgAll = "file:src/main/java/org/example/demo/Image/bgAll.png";

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
        Label titleLabel = new Label("Login Admin");
        titleLabel.setFont(new Font("System Bold", 36));

        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(new Font(18));

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font(18));

        Label errorLabel = new Label();
        errorLabel.setFont(new Font(18));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);

        // Create the TextFields
        TextField usernameTextField = new TextField();
        usernameTextField.setPrefSize(200, 40);
        usernameTextField.setMaxWidth(200);

        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPrefSize(200, 40);
        passwordTextField.setMaxWidth(200);

        // Create the Buttons
        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("System Bold", 18));
        loginButton.setPrefSize(100, 40);

        loginButton.setOnAction(actionEvent -> {
            errorLabel.setText("");
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();

            if (username.isEmpty()) {
                errorLabel.setText("Username empty");
                return;
            }
            if (password.isEmpty()) {
                errorLabel.setText("Password empty");
                return;
            }

            // Check if waiting time is required due to multiple failed attempts
            if (lastAttemptTime != null && attempts >= 3) {
                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime unlockTime = lastAttemptTime.plusSeconds(5); // Menunggu 5 detik
                if (currentTime.isBefore(unlockTime)) {
                    // Menghitung sisa waktu untuk menunggu
                    long secondsRemaining = ChronoUnit.SECONDS.between(currentTime, unlockTime);
                    errorLabel.setText("Too many attempts. Please wait " + secondsRemaining + " seconds.");
                    return;
                } else {
                    // Reset attempts dan lastAttemptTime karena waktu tunggu telah berakhir
                    attempts = 0;
                    lastAttemptTime = null;
                }
            }

            // Validasi username dan password
            if (username.equals("adnan") && password.equals("admin")) {
                // Login berhasil
                MenuAdmin menuAdmin = new MenuAdmin();
                menuAdmin.start(primaryStage);
            } else {
                // Login gagal
                attempts++;
                lastAttemptTime = LocalDateTime.now();
                if (attempts < 3) {
                    errorLabel.setText("Username or password incorrect. Tersisa " + (3 - attempts) + " attempts.");
                } else {
                    errorLabel.setText("Kesempatan habis. Please wait 5 seconds.");
                }
            }
        });

        // Add event handlers for Enter key press
        usernameTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        passwordTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        Button studentLoginButton = new Button("Login Student");
        studentLoginButton.setFont(new Font("System Bold", 14));
        studentLoginButton.setPrefSize(150, 40);

        studentLoginButton.setOnAction(actionEvent -> {
            LoginStudent loginStudent = new LoginStudent();
            loginStudent.start(primaryStage);
        });

        // Create a VBox to center all components
        VBox vbox = new VBox(10, titleLabel, usernameLabel, usernameTextField, passwordLabel, passwordTextField, loginButton, errorLabel);
        vbox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(20, studentLoginButton);
        hBox.setAlignment(Pos.BOTTOM_LEFT);

        // Create a StackPane to center the VBox in the scene
        StackPane stackPane = new StackPane(vbox);
        stackPane.setAlignment(Pos.CENTER);

        root.getChildren().add(stackPane);
        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);

        root.getChildren().add(studentLoginButton);
        AnchorPane.setBottomAnchor(studentLoginButton, 20.0);
        AnchorPane.setLeftAnchor(studentLoginButton, 20.0);

        // Create the Scene and set it on the Stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login Form");
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
