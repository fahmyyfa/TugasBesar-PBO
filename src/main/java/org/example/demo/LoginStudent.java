package org.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.demo.Database.User;
import org.example.demo.Student.MenuStudent;

import static org.example.demo.LoginAdmin.bgAll;

public class LoginStudent extends Application {
    public static TextField nimTextField = new TextField();

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
        Label titleLabel = new Label("Login Student");
        titleLabel.setFont(new Font("System Bold", 36));

        Label nimLabel = new Label("NIM");
        nimLabel.setFont(new Font(18));

        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(new Font(18));

        Label errorLabel = new Label();
        errorLabel.setFont(new Font(18));
        errorLabel.setTextFill(Color.RED);
        errorLabel.setAlignment(Pos.CENTER);

        // Create the TextFields
        nimTextField.setPrefSize(250, 40);
        nimTextField.setMaxWidth(250);      // Set max width

        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setPrefSize(250, 40);
        passwordTextField.setMaxWidth(250);      // Set max width

        // Create the Buttons
        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("System Bold", 18));
        loginButton.setPrefSize(100, 40);

        loginButton.setOnAction(actionEvent -> {
            errorLabel.setText("");
            String nim = nimTextField.getText();
            String password = passwordTextField.getText();
            if(nim.isEmpty()) {
                errorLabel.setText("NIM empty.");
                return;
            }
            if(password.isEmpty()) {
                errorLabel.setText("Password empty.");
                return;
            }

            boolean find = false;
            for (int i = 0; i < User.students.size(); i++) {
                if(nim.equals(User.students.get(i).getNim())) {
                    find = true;
                    if(nim.equals(password)) {
                        User.loginStudent = nim;
                        MenuStudent menuStudent = new MenuStudent();
                        menuStudent.start(primaryStage);
                    } else {
                        errorLabel.setText("Incorrect password.");
                    }
                }
            }
            if(!find) {
                errorLabel.setText("NIM not found.");
            }
        });

        // Add event handler for Enter key press
        passwordTextField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                loginButton.fire();
            }
        });

        Button adminLoginButton = new Button("Login Admin");
        adminLoginButton.setFont(new Font("System Bold", 14));
        adminLoginButton.setPrefSize(110, 40);

        adminLoginButton.setOnAction(actionEvent -> {
            LoginAdmin loginAdmin = new LoginAdmin();
            loginAdmin.start(primaryStage);
        });

        // Create a VBox to center all components
        VBox vbox = new VBox(10, titleLabel, nimLabel, nimTextField, passwordLabel, passwordTextField, loginButton, errorLabel);
        vbox.setAlignment(Pos.CENTER);

        HBox hBox = new HBox(20, adminLoginButton);
        hBox.setAlignment(Pos.BOTTOM_LEFT);

        // Create a StackPane to center the VBox in the scene
        StackPane stackPane = new StackPane(vbox);
        stackPane.setAlignment(Pos.CENTER);

        root.getChildren().add(stackPane);
        AnchorPane.setTopAnchor(stackPane, 0.0);
        AnchorPane.setRightAnchor(stackPane, 0.0);
        AnchorPane.setBottomAnchor(stackPane, 0.0);
        AnchorPane.setLeftAnchor(stackPane, 0.0);

        root.getChildren().add(adminLoginButton);
        AnchorPane.setBottomAnchor(adminLoginButton, 20.0);
        AnchorPane.setLeftAnchor(adminLoginButton, 20.0);


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
